package tungpt.wizelineremotechallenge.databinding;
import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.BR;
import android.view.View;
public class ToolbarBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.profile_image, 1);
        sViewsWithIds.put(R.id.screen_title, 2);
        sViewsWithIds.put(R.id.btn_search, 3);
    }
    // views
    public final android.widget.ImageButton btnSearch;
    public final android.widget.ImageView profileImage;
    public final android.widget.TextView screenTitle;
    public final android.support.v7.widget.Toolbar toolbar;
    // variables
    private tungpt.wizelineremotechallenge.views.viewmodels.ToolbarVM mViewModel;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ToolbarBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.btnSearch = (android.widget.ImageButton) bindings[3];
        this.profileImage = (android.widget.ImageView) bindings[1];
        this.screenTitle = (android.widget.TextView) bindings[2];
        this.toolbar = (android.support.v7.widget.Toolbar) bindings[0];
        this.toolbar.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
                setViewModel((tungpt.wizelineremotechallenge.views.viewmodels.ToolbarVM) variable);
                return true;
        }
        return false;
    }

    public void setViewModel(tungpt.wizelineremotechallenge.views.viewmodels.ToolbarVM ViewModel) {
        this.mViewModel = ViewModel;
    }
    public tungpt.wizelineremotechallenge.views.viewmodels.ToolbarVM getViewModel() {
        return mViewModel;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ToolbarBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ToolbarBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ToolbarBinding>inflate(inflater, tungpt.wizelineremotechallenge.R.layout.toolbar, root, attachToRoot, bindingComponent);
    }
    public static ToolbarBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ToolbarBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(tungpt.wizelineremotechallenge.R.layout.toolbar, null, false), bindingComponent);
    }
    public static ToolbarBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ToolbarBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/toolbar_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ToolbarBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): viewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}