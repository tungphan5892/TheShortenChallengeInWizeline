package tungpt.wizelineremotechallenge.databinding;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.BR;
import android.view.View;
public class UserProfileViewBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(6);
        sIncludes.setIncludes(0, 
            new String[] {"toolbar"},
            new int[] {1},
            new int[] {R.layout.toolbar});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.under_line, 2);
        sViewsWithIds.put(R.id.tweets_recycler_view, 3);
        sViewsWithIds.put(R.id.tweets_recycler_progress_dialog, 4);
        sViewsWithIds.put(R.id.fragment_replace_layout, 5);
    }
    // views
    public final android.widget.FrameLayout fragmentReplaceLayout;
    private final tungpt.wizelineremotechallenge.databinding.ToolbarBinding mboundView0;
    public final android.widget.RelativeLayout parentView;
    public final android.widget.ProgressBar tweetsRecyclerProgressDialog;
    public final android.support.v7.widget.RecyclerView tweetsRecyclerView;
    public final android.widget.FrameLayout underLine;
    // variables
    private tungpt.wizelineremotechallenge.views.viewmodels.UserProfileVM mViewModel;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public UserProfileViewBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.fragmentReplaceLayout = (android.widget.FrameLayout) bindings[5];
        this.mboundView0 = (tungpt.wizelineremotechallenge.databinding.ToolbarBinding) bindings[1];
        setContainedBinding(this.mboundView0);
        this.parentView = (android.widget.RelativeLayout) bindings[0];
        this.parentView.setTag(null);
        this.tweetsRecyclerProgressDialog = (android.widget.ProgressBar) bindings[4];
        this.tweetsRecyclerView = (android.support.v7.widget.RecyclerView) bindings[3];
        this.underLine = (android.widget.FrameLayout) bindings[2];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        mboundView0.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (mboundView0.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
            case BR.viewModel :
                setViewModel((tungpt.wizelineremotechallenge.views.viewmodels.UserProfileVM) variable);
                return true;
        }
        return false;
    }

    public void setViewModel(tungpt.wizelineremotechallenge.views.viewmodels.UserProfileVM ViewModel) {
        this.mViewModel = ViewModel;
    }
    public tungpt.wizelineremotechallenge.views.viewmodels.UserProfileVM getViewModel() {
        return mViewModel;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModel((tungpt.wizelineremotechallenge.views.viewmodels.UserProfileVM) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModel(tungpt.wizelineremotechallenge.views.viewmodels.UserProfileVM ViewModel, int fieldId) {
        switch (fieldId) {
            case BR._all: {
                synchronized(this) {
                        mDirtyFlags |= 0x1L;
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
        // batch finished
        executeBindingsOn(mboundView0);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static UserProfileViewBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static UserProfileViewBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<UserProfileViewBinding>inflate(inflater, tungpt.wizelineremotechallenge.R.layout.user_profile_view, root, attachToRoot, bindingComponent);
    }
    public static UserProfileViewBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static UserProfileViewBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(tungpt.wizelineremotechallenge.R.layout.user_profile_view, null, false), bindingComponent);
    }
    public static UserProfileViewBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static UserProfileViewBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/user_profile_view_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new UserProfileViewBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): viewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}