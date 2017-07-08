package com.studios.holtzapfel.menumaker.model.interfaces;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

/**
 * Created by holtzapfel on 6/27/17.
 */

public interface IBodyItem<T, VH extends RecyclerView.ViewHolder> extends IMenuItem<T, VH> {
    T withTitle(@Nullable String title);

    String getTitle();

    T withTitleTextColor(@ColorRes int colorRes);

    int getTitleTextColorRes();

    T withDescription(@Nullable String description);

    String getDescription();

    T withDescriptionTextColor(@ColorRes int colorRes);

    int getDescriptionTextColorRes();

    T withValue(@Nullable String value);

    String getValue();

    interface OnVerifyInputListener{
        boolean onVerifyInput(CharSequence input);
        void afterVerification(boolean inputVerified);
    }

    T withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle);

    T withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, @Nullable OnVerifyInputListener verifyInputListener);

    T withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, int inputType);

    T withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, int inputType, @Nullable OnVerifyInputListener verifyInputListener);

    T withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, int inputType, boolean verifyInputTypeEmailOrPhone);

    T withValueTextColor(@ColorRes int colorRes);

    int getValueTextColorRes();

    T withBooleanValue(boolean value);

    boolean getBooleanValue();

    T withContent(@Nullable String content);

    String getContent();

    T withContentTextColor(@ColorRes int colorRes);

    int getContentTextColorRes();

    T withIconLeft(@DrawableRes int iconRes);

    T withIconLeft(@Nullable Drawable icon);

    int getIconLeftRes();

    Drawable getIconLeft();

    T withIconLeftSize(int width, int height);

    int getIconLeftWidth();

    int getIconLeftHeight();

    T withIconLeftVisible(boolean isVisible);

    boolean isIconLeftVisible();

    T withIconLeftColor(@ColorRes int colorRes);

    int getIconLeftColorRes();

    T withIconRight(@DrawableRes int iconRes);

    T withIconRight(@Nullable Drawable icon);

    int getIconRightRes();

    Drawable getIconRight();

    T withIconRightSize(int width, int height);

    int getIconRightWidth();

    int getIconRightHeight();

    T withIconRightVisible(boolean isVisible);

    boolean isIconRightVisible();

    T withIconRightColor(@ColorRes int colorRes);

    int getIconRightColorRes();

    T withDividerEnabled(boolean isEnabled);

    boolean isDividerEnabled();

    T withDividerColor(@ColorRes int colorRes);

    int getDividerColorRes();
}
