package tungpt.wizelineremotechallenge.databinding;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.BR;
import android.view.View;
public class LeftDrawerMenuViewBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.user_background_image, 4);
        sViewsWithIds.put(R.id.user_profile_image, 5);
    }
    // views
    public final android.widget.ImageView userBackgroundImage;
    public final android.widget.TextView userDescription;
    public final android.widget.RelativeLayout userInfo;
    public final android.widget.TextView userName;
    public final android.widget.ImageView userProfileImage;
    public final android.widget.ProgressBar userViewProgressDialog;
    // variables
    private tungpt.wizelineremotechallenge.views.viewmodels.LeftDrawerMenuVM mViewModel;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public LeftDrawerMenuViewBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 2);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.userBackgroundImage = (android.widget.ImageView) bindings[4];
        this.userDescription = (android.widget.TextView) bindings[2];
        this.userDescription.setTag(null);
        this.userInfo = (android.widget.RelativeLayout) bindings[0];
        this.userInfo.setTag(null);
        this.userName = (android.widget.TextView) bindings[1];
        this.userName.setTag(null);
        this.userProfileImage = (android.widget.ImageView) bindings[5];
        this.userViewProgressDialog = (android.widget.ProgressBar) bindings[3];
        this.userViewProgressDialog.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x20L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
            case BR.viewModel :
                setViewModel((tungpt.wizelineremotechallenge.views.viewmodels.LeftDrawerMenuVM) variable);
                return true;
        }
        return false;
    }

    public void setViewModel(tungpt.wizelineremotechallenge.views.viewmodels.LeftDrawerMenuVM ViewModel) {
        updateRegistration(1, ViewModel);
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }
    public tungpt.wizelineremotechallenge.views.viewmodels.LeftDrawerMenuVM getViewModel() {
        return mViewModel;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelLeftDrawerMenuModel((tungpt.wizelineremotechallenge.views.models.LeftDrawerMenuModel) object, fieldId);
            case 1 :
                return onChangeViewModel((tungpt.wizelineremotechallenge.views.viewmodels.LeftDrawerMenuVM) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelLeftDrawerMenuModel(tungpt.wizelineremotechallenge.views.models.LeftDrawerMenuModel ViewModelLeftDrawerMenuModel, int fieldId) {
        switch (fieldId) {
            case BR.userDescription: {
                synchronized(this) {
                        mDirtyFlags |= 0x4L;
                }
                return true;
            }
            case BR.userName: {
                synchronized(this) {
                        mDirtyFlags |= 0x8L;
                }
                return true;
            }
            case BR.showLoading: {
                synchronized(this) {
                        mDirtyFlags |= 0x10L;
                }
                return true;
            }
            case BR._all: {
                synchronized(this) {
                        mDirtyFlags |= 0x1L;
                }
                return true;
            }
        }
        return false;
    }
    private boolean onChangeViewModel(tungpt.wizelineremotechallenge.views.viewmodels.LeftDrawerMenuVM ViewModel, int fieldId) {
        switch (fieldId) {
            case BR._all: {
                synchronized(this) {
                        mDirtyFlags |= 0x2L;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        boolean viewModelLeftDrawerMenuModelShowLoading = false;
        int viewModelLeftDrawerMenuModelShowLoadingViewVISIBLEViewGONE = 0;
        java.lang.String viewModelLeftDrawerMenuModelUserDescription = null;
        java.lang.String viewModelLeftDrawerMenuModelUserName = null;
        tungpt.wizelineremotechallenge.views.models.LeftDrawerMenuModel viewModelLeftDrawerMenuModel = null;
        tungpt.wizelineremotechallenge.views.viewmodels.LeftDrawerMenuVM viewModel = mViewModel;

        if ((dirtyFlags & 0x3fL) != 0) {



                if (viewModel != null) {
                    // read viewModel.leftDrawerMenuModel
                    viewModelLeftDrawerMenuModel = viewModel.getLeftDrawerMenuModel();
                }
                updateRegistration(0, viewModelLeftDrawerMenuModel);

            if ((dirtyFlags & 0x33L) != 0) {

                    if (viewModelLeftDrawerMenuModel != null) {
                        // read viewModel.leftDrawerMenuModel.showLoading
                        viewModelLeftDrawerMenuModelShowLoading = viewModelLeftDrawerMenuModel.isShowLoading();
                    }
                if((dirtyFlags & 0x33L) != 0) {
                    if(viewModelLeftDrawerMenuModelShowLoading) {
                            dirtyFlags |= 0x80L;
                    }
                    else {
                            dirtyFlags |= 0x40L;
                    }
                }


                    // read viewModel.leftDrawerMenuModel.showLoading ? View.VISIBLE : View.GONE
                    viewModelLeftDrawerMenuModelShowLoadingViewVISIBLEViewGONE = ((viewModelLeftDrawerMenuModelShowLoading) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x27L) != 0) {

                    if (viewModelLeftDrawerMenuModel != null) {
                        // read viewModel.leftDrawerMenuModel.userDescription
                        viewModelLeftDrawerMenuModelUserDescription = viewModelLeftDrawerMenuModel.getUserDescription();
                    }
            }
            if ((dirtyFlags & 0x2bL) != 0) {

                    if (viewModelLeftDrawerMenuModel != null) {
                        // read viewModel.leftDrawerMenuModel.userName
                        viewModelLeftDrawerMenuModelUserName = viewModelLeftDrawerMenuModel.getUserName();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x2bL) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.userDescription, viewModelLeftDrawerMenuModelUserName);
        }
        if ((dirtyFlags & 0x27L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.userName, viewModelLeftDrawerMenuModelUserDescription);
        }
        if ((dirtyFlags & 0x33L) != 0) {
            // api target 1

            this.userViewProgressDialog.setVisibility(viewModelLeftDrawerMenuModelShowLoadingViewVISIBLEViewGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static LeftDrawerMenuViewBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static LeftDrawerMenuViewBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<LeftDrawerMenuViewBinding>inflate(inflater, tungpt.wizelineremotechallenge.R.layout.left_drawer_menu_view, root, attachToRoot, bindingComponent);
    }
    public static LeftDrawerMenuViewBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static LeftDrawerMenuViewBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(tungpt.wizelineremotechallenge.R.layout.left_drawer_menu_view, null, false), bindingComponent);
    }
    public static LeftDrawerMenuViewBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static LeftDrawerMenuViewBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/left_drawer_menu_view_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new LeftDrawerMenuViewBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): viewModel.leftDrawerMenuModel
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): viewModel.leftDrawerMenuModel.userDescription
        flag 3 (0x4L): viewModel.leftDrawerMenuModel.userName
        flag 4 (0x5L): viewModel.leftDrawerMenuModel.showLoading
        flag 5 (0x6L): null
        flag 6 (0x7L): viewModel.leftDrawerMenuModel.showLoading ? View.VISIBLE : View.GONE
        flag 7 (0x8L): viewModel.leftDrawerMenuModel.showLoading ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}