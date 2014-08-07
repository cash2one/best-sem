package com.perfect.schedule.core.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScheduleWatcher implements Watcher {
	private static transient Logger log = LoggerFactory.getLogger(ScheduleWatcher.class);
	private Map<String,Watcher> route = new ConcurrentHashMap<String,Watcher>();
	private ZKManager manager;
	public ScheduleWatcher(ZKManager aManager){
		this.manager = aManager;
	}
	public void registerChildrenChanged(String path,Watcher watcher) throws Exception {
		manager.getZooKeeper().getChildren(path, true);
		route.put(path,watcher);
	}
	public void process(WatchedEvent event) {
		if(log.isInfoEnabled()){
			log.info("" + event.getType() + ":"+ event.getState() + "" + event.getPath());
		}
		if(event.getType() == Event.EventType.NodeChildrenChanged){
			String path = event.getPath();
			Watcher watcher = route.get(path);
			  if( watcher != null ){
				  try{
					  watcher.process(event);
				  }finally{
					  try{
						  if(manager.getZooKeeper().exists(path,null) != null){
							  manager.getZooKeeper().getChildren(path, true);
						  }
					  }catch(Exception e){
						  log.error(path +":" + e.getMessage(),e);
					  }
				  }
			  }else{
				  log.info("" + event.getType() + ":"+ event.getState() + "" + event.getPath());
			  }
		}else if (event.getState() == KeeperState.SyncConnected) {
			log.info("");
		} else if (event.getState() == KeeperState.Expired) {
			log.error("");
			try {
				manager.reConnection();
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
	}
}