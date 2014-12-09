package com.perfect.api.baidu;

/**
 * Created by baizz on 2014-7-15.
 * @see BaiduSearchPageUtils
 */

@Deprecated

public class BaiduPreviewHelper {

//    private static Logger logger = LoggerFactory.getLogger(BaiduPreviewHelper.class);
//
//    //模糊匹配
//    public boolean vagueMatch(String keyword, String matchWord) {
//        char[] chars1 = keyword.toCharArray();
//        char[] chars2 = matchWord.toCharArray();
//        Arrays.sort(chars1);
//        Arrays.sort(chars2);
//        keyword = String.valueOf(chars1);
//        matchWord = String.valueOf(chars2);
//        return keyword.equals(matchWord);
//    }
//    //从模拟页面获取推广链接和关键词
//
//    public List<PreviewData> getPageData(String[] keyword, Integer region) {
//
//
//        List<CreativeInfoDTO> leftCreativeVOList = new LinkedList<>();
//        List<CreativeInfoDTO> rightCreativeVOList = new LinkedList<>();
//
//        Map<String, String> htmls = getHTML(getPreviewRequest, serviceFactory);
//
//        if (htmls == null)
//            return Collections.EMPTY_LIST;
//
//        List<PreviewData> previewDatas = new ArrayList<>(htmls.size());
//
//        for (Map.Entry<String, String> htmlEntry : htmls.entrySet()) {
//            Document doc = Jsoup.parse(htmlEntry.getValue());
//
//            handleLeft(doc, leftCreativeVOList);
//            handleRight(doc, rightCreativeVOList);
//
//            PreviewData previewData = new PreviewData();
//
//            previewData.setKeyword(htmlEntry.getKey());
//            previewData.setRegion(getPreviewRequest.getRegion());
//            previewData.setDevice(getPreviewRequest.getDevice());
//            previewData.setLeft(leftCreativeVOList);
//            previewData.setRight(rightCreativeVOList);
//            previewDatas.add(previewData);
//        }
//
//        return previewDatas;
//    }
//
//    private static void handleRight(Document doc, final List<CreativeInfoDTO> rightCreativeVOList) {
//        Elements div_right = null;
//        boolean _temp2 = (doc.getElementById("ec_im_container") != null) && (doc.getElementById("ec_im_container").children().size() > 0);
//
//        if (_temp2) {
//            div_right = doc.getElementById("ec_im_container").children();
//        }
//
//        //过滤出右侧的推广信息
//        if (_temp2) {
//            for (int j = div_right.size() - 1; j >= 0; j--) {
//                if (!div_right.get(j).hasAttr("id")) {
//                    div_right.remove(j);
//                }
//            }
//        }
//
//        //获取右侧推广数据
//        if (_temp2 && div_right.size() > 0) {
//            CreativeInfoDTO creativeVO = null;
//            for (Element e : div_right) {
//                String _title = e.select("a").first().text();
//                String _description = e.select("a").get(1).text();
//                String _url = e.select("a").get(1).select("font").last().text();
//                _description.replace(_url, "");
//
//                creativeVO = new CreativeInfoDTO();
//                creativeVO.setDescSource(e.html());
//                creativeVO.setTitle(_title);
//                creativeVO.setDescription(_description);
//                creativeVO.setUrl(_url);
//                rightCreativeVOList.add(creativeVO);
//            }
//        }
//    }
//
//    private static void handleLeft(Document doc, final List<CreativeInfoDTO> leftCreativeVOList) {
//        LinkedList<CreativeInfoDTO> creativeInfoDTOList = new LinkedList<>();
//
//        //获取左侧推广数据
//        if (doc.select("#content_left > table").isEmpty()) {
//            //div
//            // ec_title
//            Elements elements = doc.select("#content_left > .Ec_result");
//            for (Element element : elements) {
//                if (element.attr("id").startsWith("5"))
//                    continue;
//
//                CreativeInfoDTO creativeInfoDTO = new CreativeInfoDTO();
//                creativeInfoDTO.setDescSource(element.html());
//                creativeInfoDTO.setTitle(element.select(".ec_title").text());
//
//                creativeInfoDTO.setTitle(element.select(".ec_title").text());
//                if (element.select(".ec_desc .EC_pap_big_desc").size() > 0) {
//                    creativeInfoDTO.setDescription(element.select(".ec_desc .EC_pap_big_desc").text());
//                    creativeInfoDTO.setUrl(element.select(".ec_desc .ec_meta .ec_url").text());
//                    creativeInfoDTO.setTime(element.select(".ec_desc .ec_meta .ec_date").text());
//                    Elements children = element.select(".ec_desc .EC_pap_big_paxj a");
//                    if (children != null) {
//                        List<SublinkInfoDTO> sublinkInfos = new ArrayList<>();
//                        ListIterator<Element> iterator = children.listIterator();
//                        while (iterator.hasNext()) {
//                            SublinkInfoDTO sublinkInfo = new SublinkInfoDTO();
//                            sublinkInfo.setDescription(iterator.next().text());
//                            sublinkInfos.add(sublinkInfo);
//                        }
//                        creativeInfoDTO.setSublinkInfoDTOs(sublinkInfos);
//                    }
//                } else {
//                    creativeInfoDTO.setDescription(element.select(".ec_desc").text());
//                    creativeInfoDTO.setUrl(element.select(".ec_meta .ec_url").text());
//                    creativeInfoDTO.setTime(element.select(".ec_meta .ec_date").text());
//                }
//                creativeInfoDTOList.addLast(creativeInfoDTO);
//
//            }
//
//        } else {
//            Elements tables = doc.select("#content_left table");
//            for (Element table : tables) {
//                if (!table.className().startsWith(" ")) {
//                    continue;
//                }
//                CreativeInfoDTO creativeInfoDTO = new CreativeInfoDTO();
//                creativeInfoDTO.setDescSource(table.html());
//                creativeInfoDTO.setTitle(table.select(".EC_title").text());
//
//                Elements descs = table.select(".EC_body");
//                if (descs.isEmpty()) {
//                    creativeInfoDTO.setDescription(table.select(".EC_pap_big_zpdes").text());
//
//                    // 处理XJ
////                    Elements xjs = table.select(".EC_pap_big_xj");
//                } else {
//                    creativeInfoDTO.setDescription(descs.text());
//                }
//
//                Elements sublinks = table.select(".EC_xj_underline a");
//                if (!sublinks.isEmpty()) {
//                    ListIterator<Element> children = sublinks.listIterator();
//                    List<SublinkInfoDTO> sublinkInfos = new ArrayList<>();
//                    while (children.hasNext()) {
//                        SublinkInfoDTO sublinkInfo = new SublinkInfoDTO();
//                        sublinkInfo.setDescription(children.next().text());
//                        sublinkInfos.add(sublinkInfo);
//                    }
//                    creativeInfoDTO.setSublinkInfoDTOs(sublinkInfos);
//                }
//
//                creativeInfoDTO.setUrl(table.select(".EC_url").text());
//
//                creativeInfoDTOList.addLast(creativeInfoDTO);
//            }
//        }
//        leftCreativeVOList.addAll(creativeInfoDTOList);
//    }
//
//    private Map<String, String> getHTML(GetPreviewRequest getPreviewRequest, CommonService commonService) {
//
//        Preview
//        Map<String, String> htmls = new HashMap<>();
//
//        try {
//            htmls.put(getPreviewRequest.getKeyWord(0), response.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
////        GetPreviewResponse response = requestHelper.addRequest(commonService, getPreviewRequest);
////        if (response == null)
////            return null;
////        List<PreviewInfo> list1 = response.getPreviewInfos();
////        if (list1 == null || list1.isEmpty()) {
////            return null;
////        }
////
////        Map<String, String> htmls = new HashMap<>();
////
////        for (PreviewInfo info : list1) {
////            try {
////                htmls.put(info.getKeyword(), uncompress(info.getData()));
////            } catch (IOException e1) {
////                e1.printStackTrace();
////            }
////        }
//        return htmls;
//    }
//
//    private String uncompress(String str) throws IOException {
//        if (str == null || str.length() == 0)
//            return str;
//
//        byte[] bytes = Base64.decodeBase64(str);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
//        GZIPInputStream gunzip = new GZIPInputStream(in);
//        byte[] buffer = new byte[256];
//        int n;
//        while ((n = gunzip.read(buffer)) >= 0) {
//            out.write(buffer, 0, n);
//        }
//        return out.toString("utf-8");
//    }
//
//    public static List<PreviewData> crawl(String keyword, Integer region) {
//
////        Future<String> response = RequestHelper.addRequest(keyword, region);
////        Map<String, String> htmls = new HashMap<>();
////
////        try {
////            while (!response.isDone()) {
////                Thread.sleep(100);
////            }
////            String html = response.get();
////
////            htmls.put(keyword, html);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        } catch (ExecutionException e) {
////            e.printStackTrace();
////        }
////
////
////        List<CreativeDTO> leftCreativeVOList = new LinkedList<>();
////        List<CreativeDTO> rightCreativeVOList = new LinkedList<>();
////
////
////        if (htmls == null)
////            return Collections.EMPTY_LIST;
////
////        List<PreviewData> previewDatas = new ArrayList<>(htmls.size());
////
////        for (Map.Entry<String, String> htmlEntry : htmls.entrySet()) {
////            Document doc = Jsoup.parse(htmlEntry.getValue());
////
////            handleLeft(doc, leftCreativeVOList);
////            handleRight(doc, rightCreativeVOList);
////
////            List<CreativeDTO> list = new ArrayList<>();
////            list.addAll(leftCreativeVOList);
////            list.addAll(rightCreativeVOList);
////
////            try {
////                EsRunnable esRunnable = new EsRunnable();
////                esRunnable.setKeyword(keyword);
////                esRunnable.setRegion(region);
////                esRunnable.setList(list);
////
////                EsThreadPoolTaskExecutor.execute(esRunnable);
////            } catch (Exception e) {
////                if (logger.isErrorEnabled()) {
////                    logger.error("ES error", e);
////                }
////            }
////
////            PreviewData previewData = new PreviewData();
////
////            previewData.setKeyword(htmlEntry.getKey());
////            previewData.setRegion(region);
////            previewData.setDevice(1);
////            previewData.setLeft(leftCreativeVOList);
////            previewData.setRight(rightCreativeVOList);
////            previewDatas.add(previewData);
////        }
////
////        return previewDatas;
//
//        return null;
//
//    }
//
//
//    public static class PreviewData {
//
//        private String keyword;
//
//        private int region;
//
//        private List<CreativeInfoDTO> left;
//
//        private List<CreativeInfoDTO> right;
//        private Integer device;
//
//        public List<CreativeInfoDTO> getLeft() {
//            return left;
//        }
//
//        public void setLeft(List<CreativeInfoDTO> left) {
//            this.left = left;
//        }
//
//        public List<CreativeInfoDTO> getRight() {
//            return right;
//        }
//
//        public void setRight(List<CreativeInfoDTO> right) {
//            this.right = right;
//        }
//
//        public String getKeyword() {
//            return keyword;
//        }
//
//        public void setKeyword(String keyword) {
//            this.keyword = keyword;
//        }
//
//        public int getRegion() {
//            return region;
//        }
//
//        public void setRegion(int region) {
//            this.region = region;
//        }
//
//        public void setDevice(Integer device) {
//            this.device = device;
//        }
//
//        public Integer getDevice() {
//            return device;
//        }
//    }

}