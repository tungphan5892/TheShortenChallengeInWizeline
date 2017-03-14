package tungpt.wizelineremotechallenge.views.models;

/**
 * Created by tungphan on 3/13/17.
 */

public class ToolbarModel {

    private boolean visibleBtnClose = false;
    private boolean visibleBtnSearch = true;

    public void setVisibleBtnClose(boolean visible){
        visibleBtnClose = visible;
    }

    public boolean getVisibleBtnClose(){
        return visibleBtnClose;
    }

    public void setVisibleBtnSearch(boolean visible){
        visibleBtnSearch = visible;
    }

    public boolean getVisibleBtnSearch(){
        return visibleBtnSearch;
    }


}
