// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;

// Referenced classes of package android.support.v7.widget:
//            SearchView

public static class tManager extends AutoCompleteTextView
{

    private final int POPUP_WINDOW_ATTRS[] = {
        0x1010176
    };
    private SearchView mSearchView;
    private int mThreshold;
    private final TintManager mTintManager;

    private boolean isEmpty()
    {
        return TextUtils.getTrimmedLength(getText()) == 0;
    }

    public boolean enoughToFilter()
    {
        return mThreshold <= 0 || super.enoughToFilter();
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        super.onFocusChanged(flag, i, rect);
        mSearchView.onTextFocusChanged();
    }

    public boolean onKeyPreIme(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            if (keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
            {
                android.view.AutoComplete autocomplete = getKeyDispatcherState();
                if (autocomplete != null)
                {
                    autocomplete.Tracking(keyevent, this);
                }
                return true;
            }
            if (keyevent.getAction() == 1)
            {
                android.view.AutoComplete autocomplete1 = getKeyDispatcherState();
                if (autocomplete1 != null)
                {
                    autocomplete1.eUpEvent(keyevent);
                }
                if (keyevent.isTracking() && !keyevent.isCanceled())
                {
                    mSearchView.clearFocus();
                    SearchView.access$2200(mSearchView, false);
                    return true;
                }
            }
        }
        return super.onKeyPreIme(i, keyevent);
    }

    public void onWindowFocusChanged(boolean flag)
    {
        super.onWindowFocusChanged(flag);
        if (flag && mSearchView.hasFocus() && getVisibility() == 0)
        {
            ((InputMethodManager)getContext().getSystemService("input_method")).showSoftInput(this, 0);
            if (SearchView.isLandscapeMode(getContext()))
            {
                SearchView.HIDDEN_METHOD_INVOKER.ensureImeVisible(this, true);
            }
        }
    }

    public void performCompletion()
    {
    }

    protected void replaceText(CharSequence charsequence)
    {
    }

    public void setDropDownBackgroundResource(int i)
    {
        setDropDownBackgroundDrawable(mTintManager.getDrawable(i));
    }

    void setSearchView(SearchView searchview)
    {
        mSearchView = searchview;
    }

    public void setThreshold(int i)
    {
        super.setThreshold(i);
        mThreshold = i;
    }


    public wReflector(Context context)
    {
        this(context, null);
    }

    public <init>(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101006b);
    }

    public <init>(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mThreshold = getThreshold();
        context = TintTypedArray.obtainStyledAttributes(context, attributeset, POPUP_WINDOW_ATTRS, i, 0);
        if (context.hasValue(0))
        {
            setDropDownBackgroundDrawable(context.getDrawable(0));
        }
        context.recycle();
        mTintManager = context.getTintManager();
    }
}
