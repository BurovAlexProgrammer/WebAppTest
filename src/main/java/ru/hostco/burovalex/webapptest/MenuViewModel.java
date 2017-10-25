package ru.hostco.burovalex.webapptest;

import java.util.LinkedHashMap;

public class MenuViewModel {

    LinkedHashMap pm;
    void Init() {
        pm = new LinkedHashMap<String, Map<String, NavigationPage>>();

    }


    private void addPage(String title, String subTitle, String includeUri, String data) {
        String folder = "/widgets/menu/navbar/pages";
        Map<String, NavigationPage> subPageMap = pageMap.get(title);
        if(subPageMap == null) {
            subPageMap = new LinkedHashMap<String, NavigationPage>();
            pageMap.put(title, subPageMap);
        }
        NavigationPage navigationPage = new NavigationPage(title, subTitle,
                folder + includeUri + "?random=" + Math.random(), data) {
            @Override
            public boolean isSelected() {
                return currentPage == this;
            }
        };
        subPageMap.put(subTitle, navigationPage);
    }
}
