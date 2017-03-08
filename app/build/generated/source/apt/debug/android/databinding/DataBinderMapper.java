
package android.databinding;
import tungpt.wizelineremotechallenge.BR;
class DataBinderMapper  {
    final static int TARGET_MIN_SDK = 23;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case tungpt.wizelineremotechallenge.R.layout.toolbar:
                    return tungpt.wizelineremotechallenge.databinding.ToolbarBinding.bind(view, bindingComponent);
                case tungpt.wizelineremotechallenge.R.layout.left_drawer_menu_view:
                    return tungpt.wizelineremotechallenge.databinding.LeftDrawerMenuViewBinding.bind(view, bindingComponent);
                case tungpt.wizelineremotechallenge.R.layout.user_profile_view:
                    return tungpt.wizelineremotechallenge.databinding.UserProfileViewBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 519370695: {
                if(tag.equals("layout/toolbar_0")) {
                    return tungpt.wizelineremotechallenge.R.layout.toolbar;
                }
                break;
            }
            case -774311685: {
                if(tag.equals("layout/left_drawer_menu_view_0")) {
                    return tungpt.wizelineremotechallenge.R.layout.left_drawer_menu_view;
                }
                break;
            }
            case -1538632453: {
                if(tag.equals("layout/user_profile_view_0")) {
                    return tungpt.wizelineremotechallenge.R.layout.user_profile_view;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"showLoading"
            ,"userDescription"
            ,"userName"
            ,"viewModel"};
    }
}