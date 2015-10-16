package com.perfect.commons.quartz;

import com.perfect.commons.context.ApplicationContextHelper;
import com.perfect.service.MaterialsUploadService;
import com.perfect.service.impl.MaterialsUploadServiceImpl;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created on 2015-09-29.
 * <p>物料上传任务执行器.
 *
 * @author dolphineor
 */
@DisallowConcurrentExecution
public class QuartzJobExecutor implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJobExecutor.class);

    private final MaterialsUploadService materialsUploadService;


    public QuartzJobExecutor() {
        materialsUploadService = ApplicationContextHelper.getBeanByClass(MaterialsUploadServiceImpl.class);
    }


    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link Trigger}</code>
     * fires that is associated with the <code>Job</code>.
     * </p>
     *
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduledJob scheduledJob = (ScheduledJob) context.getMergedJobDataMap().get("scheduledJob");
        String sysUser = scheduledJob.getJobName();

        switch (scheduledJob.getJobType()) {
            case 10: {
                LOGGER.info("==========开始上传 {} 的物料==========", sysUser);
                List<Long> result = materialsUploadService.upload(sysUser);
                if (!result.isEmpty())
                    result.forEach(id -> LOGGER.info("ID为 {} 的百度账号物料上传失败", id));

                LOGGER.info("========== {} 的物料上传结束==========", sysUser);
                break;
            }
            case 11: {
                List<Long> result = materialsUploadService.pause(sysUser);
                if (!result.isEmpty())
                    result.forEach(id -> LOGGER.info("系统用户 {} 下, ID为 {} 的百度账号暂停物料投放失败", sysUser, id));

                break;
            }
            default:
                break;
        }

    }

}
