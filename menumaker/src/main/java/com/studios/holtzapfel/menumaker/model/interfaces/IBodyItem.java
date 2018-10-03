package com.studios.holtzapfel.menumaker.model.interfaces;

import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by holtzapfel on 6/27/17.
 */

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface IBodyItem<T, VH extends RecyclerView.ViewHolder> extends IMenuItem<T, VH> {

    // TITLE
    T withTitle(@Nullable String title);
    String getTitle();
    T withTitleTextColor(@ColorRes int colorRes);
    int getTitleTextColorRes();
    T withTitleTextSize(float size);
    T withTitleTextSize(int unit, float size);
    float getTitleTextSize();
    int getTitleTextSizeUnit();

    // DESCRIPTION
    T withDescription(@Nullable String description);
    String getDescription();
    T withDescriptionTextColor(@ColorRes int colorRes);
    int getDescriptionTextColorRes();
    T withDescriptionTextSize(float size);
    T withDescriptionTextSize(int unit, float size);
    float getDescriptionTextSize();
    int getDescriptionTextSizeUnit();

    // VALUE
    T withValue(@Nullable String value);
    String getValue();
    T withValueTextColor(@ColorRes int colorRes);
    int getValueTextColorRes();
    T withValueTextSize(float size);
    T withValueTextSize(int unit, float size);
    float getValueTextSize();
    int getValueTextSizeUnit();

    // EDITABLE VALUE
    interface OnVerifyInputListener{
        boolean onVerifyInput(CharSequence input);
        void afterVerification(boolean inputVerified);
    }
    T withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle);
    T withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, @Nullable OnVerifyInputListener verifyInputListener);
    T withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, int inputType);
    T withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, int inputType, @Nullable OnVerifyInputListener verifyInputListener);
    T withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, int inputType, boolean verifyInputTypeEmailOrPhone);

    // BOOLEAN VALUE
    T withBooleanValue(boolean value);
    boolean getBooleanValue();

    // CONTENT
    T withContent(@Nullable String content);
    String getContent();
    T withContentTextColor(@ColorRes int colorRes);
    int getContentTextColorRes();
    T withContentTextSize(float size);
    T withContentTextSize(int unit, float size);
    float getContentTextSize();
    int getContentTextSizeUnit();

    // ICON LEFT
    T withIconLeft(int iconRes);
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

    // ICON RIGHT
    T withIconRight(int iconRes);
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

    // DIVIDER
    T withDividerEnabled(boolean isEnabled);
    boolean isDividerEnabled();
    T withDividerColor(@ColorRes int colorRes);
    int getDividerColorRes();
}
