/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import presenter.PagesHandler.PAGES;
import view.PageAbstract;
import view.componets.DashboardItem;

/**
 *
 * @author johnrojas
 */
public interface PageHandlerInterface {
    public void openPage(PageAbstract page);
    public void openPage(PAGES page);
    public DashboardItem getDashItemByPage(PAGES name);
    public void disableDashboardItem(DashboardItem item);
}
