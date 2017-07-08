package com.studios.holtzapfel.menumaker.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.studios.holtzapfel.menumaker.MMMenu;
import com.studios.holtzapfel.menumaker.Master;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.interfaces.IBodyItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

/**
 * Created by holtzapfel on 6/27/17.
 */

public class BodyMenuItem extends AbstractMenuItem<BodyMenuItem, BodyMenuItem.BodyMenuItemViewHolder> implements IBodyItem<BodyMenuItem, BodyMenuItem.BodyMenuItemViewHolder>{


    public BodyMenuItem(int id){
        withID(id);
    }

    private String mTitle;

    @Override
    public BodyMenuItem withTitle(@Nullable String title) {
        this.mTitle = title;
        return this;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    private int mTitleTextColorRes = -1;

    @Override
    public BodyMenuItem withTitleTextColor(@ColorRes int colorRes) {
        this.mTitleTextColorRes = colorRes;
        return this;
    }

    @Override
    public int getTitleTextColorRes() {
        return mTitleTextColorRes;
    }

    private String mDescription;

    @Override
    public BodyMenuItem withDescription(@Nullable String description) {
        this.mDescription = description;
        return this;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    private int mDescriptionTextColorRes = -1;

    @Override
    public BodyMenuItem withDescriptionTextColor(@ColorRes int colorRes) {
        this.mDescriptionTextColorRes = colorRes;
        return this;
    }

    @Override
    public int getDescriptionTextColorRes() {
        return mDescriptionTextColorRes;
    }

    private String mValue;

    @Override
    public BodyMenuItem withValue(@Nullable String value) {
        this.mValue = value;
        return this;
    }

    @Override
    public String getValue() {
        return mValue;
    }

    private boolean isValueEditable = false;
    private CharSequence mEditableHint;
    private boolean prefillWithValue = false;
    private boolean allowEmptyInput = true;
    private CharSequence mEditableTitle;
    private int mEditableInputType = -1;
    private boolean verifyIfValueIsEmail = false;
    private boolean verifyIfValueIsPhone = false;
    private OnVerifyInputListener mVerifyInputListener;
    private boolean isInputTypePassword = false;

    @Override
    public BodyMenuItem withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle) {
        this.withValue(value);
        this.isValueEditable = true;
        this.mEditableHint = inputHint;
        this.prefillWithValue = prefillInputWithItemValue;
        this.allowEmptyInput = allowEmptyInput;
        this.mEditableTitle = dialogTitle;
        return this;
    }

    @Override
    public BodyMenuItem withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, @Nullable OnVerifyInputListener verifyInputListener) {
        withValueEditable(value, inputHint, prefillInputWithItemValue, allowEmptyInput, dialogTitle);
        this.mVerifyInputListener = verifyInputListener;
        return this;
    }

    @Override
    public BodyMenuItem withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, int inputType) {
        withValueEditable(value, inputHint, prefillInputWithItemValue, allowEmptyInput, dialogTitle);
        this.mEditableInputType = inputType;
        isInputTypePassword = inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD;
        return this;
    }

    @Override
    public BodyMenuItem withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, int inputType, @Nullable OnVerifyInputListener verifyInputListener) {
        withValueEditable(value, inputHint, prefillInputWithItemValue, allowEmptyInput, dialogTitle, inputType);
        this.mVerifyInputListener = verifyInputListener;
        return this;
    }

    @Override
    public BodyMenuItem withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, int inputType, boolean verifyInputTypeEmailOrPhone) {
        withValueEditable(value, inputHint, prefillInputWithItemValue, allowEmptyInput, dialogTitle, inputType);

        if (verifyInputTypeEmailOrPhone){
            switch (inputType){
                case InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS:
                    this.verifyIfValueIsEmail = true;
                    this.verifyIfValueIsPhone = false;
                    break;
                case InputType.TYPE_CLASS_PHONE:
                    this.verifyIfValueIsPhone = true;
                    this.verifyIfValueIsEmail = true;
                    break;
            }
        } else {
            this.verifyIfValueIsEmail = false;
            this.verifyIfValueIsPhone = false;
        }
        return this;
    }

    private CharSequence getPrefillValue(){
        if (prefillWithValue){
            return mValue;
        } else return null;
    }

    private int mValueTextColorRes = -1;

    @Override
    public BodyMenuItem withValueTextColor(@ColorRes int colorRes) {
        this.mValueTextColorRes = colorRes;
        return this;
    }

    @Override
    public int getValueTextColorRes() {
        return mValueTextColorRes;
    }

    private boolean mBooleanValue = false;
    private boolean isSwitchUsed = false;

    @Override
    public BodyMenuItem withBooleanValue(boolean value) {
        this.mBooleanValue = value;
        this.isSwitchUsed = true;
        return this;
    }

    @Override
    public boolean getBooleanValue() {
        return mBooleanValue;
    }

    private String mContent;

    @Override
    public BodyMenuItem withContent(@Nullable String content) {
        this.mContent = content;
        return this;
    }

    @Override
    public String getContent() {
        return mContent;
    }

    private int mContentTextColorRes = -1;

    @Override
    public BodyMenuItem withContentTextColor(@ColorRes int colorRes) {
        this.mContentTextColorRes = colorRes;
        return this;
    }

    @Override
    public int getContentTextColorRes() {
        return mContentTextColorRes;
    }

    private int mIconLeftRes = -1;
    private Drawable mIconLeft;

    @Override
    public BodyMenuItem withIconLeft(@DrawableRes int iconRes) {
        this.mIconLeftRes = getMMIconRes(iconRes);
        if (this.mIconLeftRes != -1){
            withIconLeftSize(75, 75);
        } else this.mIconLeftRes = iconRes;
        return this;
    }

    @Override
    public BodyMenuItem withIconLeft(@Nullable Drawable icon) {
        this.mIconLeft = icon;
        return this;
    }

    @Override
    public int getIconLeftRes() {
        return mIconLeftRes;
    }

    @Override
    public Drawable getIconLeft() {
        return mIconLeft;
    }

    private int mIconLeftHeight = -1;
    private int mIconLeftWidth = -1;

    @Override
    public BodyMenuItem withIconLeftSize(int width, int height){
        this.mIconLeftWidth = width;
        this.mIconLeftHeight = height;
        return this;
    }

    @Override
    public int getIconLeftWidth(){
        return mIconLeftWidth;
    }

    @Override
    public int getIconLeftHeight(){
        return mIconLeftHeight;
    }

    private boolean isIconLeftVisible = true;

    @Override
    public BodyMenuItem withIconLeftVisible(boolean isVisible) {
        this.isIconLeftVisible = isVisible;
        return this;
    }

    @Override
    public boolean isIconLeftVisible() {
        return isIconLeftVisible;
    }

    private int mIconLeftColorRes = -1;

    @Override
    public BodyMenuItem withIconLeftColor(@ColorRes int colorRes) {
        this.mIconLeftColorRes = colorRes;
        return this;
    }

    @Override
    public int getIconLeftColorRes() {
        return mIconLeftColorRes;
    }

    private int mIconRightRes = -1;
    private Drawable mIconRight;

    @Override
    public BodyMenuItem withIconRight(@DrawableRes int iconRes) {
        this.mIconRightRes = getMMIconRes(iconRes);
        if (this.mIconRightRes != -1){
            withIconRightSize(75, 75);
        } else this.mIconRightRes = iconRes;
        return this;
    }

    @Override
    public BodyMenuItem withIconRight(@Nullable Drawable icon) {
        this.mIconRight = icon;
        return this;
    }

    @Override
    public int getIconRightRes() {
        return mIconRightRes;
    }

    @Override
    public Drawable getIconRight() {
        return mIconRight;
    }

    private boolean isIconRightVisible = true;

    @Override
    public BodyMenuItem withIconRightVisible(boolean isVisible) {
        this.isIconRightVisible = isVisible;
        return this;
    }

    @Override
    public boolean isIconRightVisible() {
        return isIconRightVisible;
    }

    private int mIconRightColorRes = -1;

    @Override
    public BodyMenuItem withIconRightColor(@ColorRes int colorRes) {
        this.mIconRightColorRes = colorRes;
        return this;
    }

    @Override
    public int getIconRightColorRes() {
        return mIconRightColorRes;
    }

    private int mIconRightHeight = -1;
    private int mIconRightWidth = -1;

    @Override
    public BodyMenuItem withIconRightSize(int width, int height){
        this.mIconRightWidth = width;
        this.mIconRightHeight = height;
        return this;
    }

    @Override
    public int getIconRightWidth(){
        return mIconRightWidth;
    }

    @Override
    public int getIconRightHeight(){
        return mIconRightHeight;
    }

    private boolean isDividerEnabled = true;

    @Override
    public BodyMenuItem withDividerEnabled(boolean isEnabled) {
        this.isDividerEnabled = isEnabled;
        return this;
    }

    @Override
    public boolean isDividerEnabled() {
        return isDividerEnabled;
    }

    private int mDividerColorRes = -1;

    @Override
    public BodyMenuItem withDividerColor(int colorRes) {
        this.mDividerColorRes = colorRes;
        return this;
    }

    @Override
    public int getDividerColorRes() {
        return mDividerColorRes;
    }

    @Override
    public int getMenuType() {
        return IMenuItem.MENU_ITEM_TYPE_BODY;
    }

    @Override
    public void bindView(final Context context, final BodyMenuItemViewHolder holder, final MMMenu.OnMenuItemClickListener listener) {
        // Configure card
        if(isEnabled()){
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isSwitchUsed){
                        holder.booleanValue.performClick();
                    } else if (isValueEditable){
                        MaterialDialog.Builder dialogEditable = new MaterialDialog.Builder(context)
                                .input(mEditableHint, getPrefillValue(), allowEmptyInput, new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                        if (mVerifyInputListener != null){
                                            boolean isVerified = mVerifyInputListener.onVerifyInput(input);
                                            mVerifyInputListener.afterVerification(isVerified);

                                            if (isVerified){
                                                withValue(input.toString());
                                                dialog.dismiss();
                                                listener.onBodyItemClick(BodyMenuItem.this);
                                            }
                                        } else if (verifyIfValueIsEmail){
                                            if (Master.verifyStringIsEmail(input.toString())){
                                                withValue(input.toString());
                                                dialog.dismiss();
                                                listener.onBodyItemClick(BodyMenuItem.this);
                                            } else Toast.makeText(context, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                                        } else if (verifyIfValueIsPhone){
                                            if (Master.verifyStringIsPhoneNumber(input.toString())){
                                                withValue(input.toString());
                                                dialog.dismiss();
                                                listener.onBodyItemClick(BodyMenuItem.this);
                                            } else Toast.makeText(context, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                                        } else {
                                            withValue(String.valueOf(input));
                                            dialog.dismiss();
                                            listener.onBodyItemClick(BodyMenuItem.this);
                                        }

                                        if (getValue() != null){
                                            holder.value.setVisibility(View.VISIBLE);
                                            holder.value.setText(Master.fromHtml(getValue()));
                                        } else holder.value.setVisibility(View.GONE);

                                    }
                                })
                                .negativeText("Cancel")
                                .onNegative(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();
                                    }
                                })
                                .autoDismiss(false);

                        if (mEditableTitle != null){
                            dialogEditable.title(mEditableTitle);
                        }

                        if (mEditableInputType != -1){
                            dialogEditable.inputType(mEditableInputType);
                        }

                        dialogEditable.show();
                    } else listener.onBodyItemClick(BodyMenuItem.this);
                }
            });
        }

        // Configure title
        if (getTitle() != null){
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(Master.fromHtml(getTitle()));

            // Set text color
            if (getTitleTextColorRes() != -1 && getTitleTextColorRes() != 0){
                holder.title.setTextColor(ResourcesCompat.getColor(context.getResources(), getTitleTextColorRes(), context.getTheme()));
            }
        } else holder.title.setVisibility(View.GONE);

        // Configure description
        if (getDescription() != null){
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(Master.fromHtml(getDescription()));

            // Set text color
            if (getDescriptionTextColorRes() > 0){
                holder.description.setTextColor(ResourcesCompat.getColor(context.getResources(), getDescriptionTextColorRes(), context.getTheme()));
            }
        } else holder.description.setVisibility(View.GONE);

        // Configure switch
        if (isSwitchUsed){
            holder.booleanValue.setVisibility(View.VISIBLE);
            holder.booleanValue.setChecked(getBooleanValue());
            holder.booleanValue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    BodyMenuItem.this.withBooleanValue(b);
                    listener.onBodyItemClick(BodyMenuItem.this);
                }
            });
        } else holder.booleanValue.setVisibility(View.GONE);

        // Configure value
        if (getValue() != null){
            holder.value.setVisibility(View.VISIBLE);
            holder.value.setText(Master.fromHtml(getValue()));

            // Convert text to hidden password if input type is password
            if (isInputTypePassword) {
                holder.value.setTransformationMethod(new PasswordTransformationMethod());
            }

            // Set text color
            if (getValueTextColorRes() > 0){
                holder.value.setTextColor(ResourcesCompat.getColor(context.getResources(), getValueTextColorRes(), context.getTheme()));
            }
        } else holder.value.setVisibility(View.GONE);

        // Configure content
        if (getContent() != null){
            holder.content.setVisibility(View.VISIBLE);
            holder.content.setText(Master.fromHtml(getContent()));

            if (getContentTextColorRes() > 0){
                holder.content.setTextColor(ResourcesCompat.getColor(context.getResources(), getContentTextColorRes(), context.getTheme()));
            }
        } else holder.content.setVisibility(View.GONE);

        // Configure left icon
        if (getIconLeftRes() != -1 || getIconLeft() != null){
            if (isIconLeftVisible()) {
                holder.iconLeft.setVisibility(View.VISIBLE);
            } else holder.iconLeft.setVisibility(View.GONE);

            if (getIconLeftRes() != -1) {
                holder.iconLeft.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), getIconLeftRes(), context.getTheme()));
            } else if (getIconLeft() != null){
                holder.iconLeft.setImageDrawable(getIconLeft());
            }

            if (getIconLeftColorRes() != -1){
                holder.iconLeft.setColorFilter(ResourcesCompat.getColor(context.getResources(), getIconLeftColorRes(), context.getTheme()));
            }

            if (getIconLeftWidth() != -1 && getIconLeftHeight() != -1){
                holder.iconLeft.getLayoutParams().width = getIconLeftWidth();
                holder.iconLeft.getLayoutParams().height = getIconLeftHeight();
            }
        } else holder.iconLeft.setVisibility(View.GONE);

        // Configure right icon
        if (getIconRightRes() != -1 || getIconRight() != null){
            if (isIconRightVisible()) {
                holder.iconRight.setVisibility(View.VISIBLE);
            } else holder.iconRight.setVisibility(View.GONE);

            if (getIconRightRes() != -1) {
                holder.iconRight.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), getIconRightRes(), context.getTheme()));
            } else if (getIconRight() != null){
                holder.iconRight.setImageDrawable(getIconRight());
            }

            if (getIconRightColorRes() != -1){
                holder.iconRight.setColorFilter(ResourcesCompat.getColor(context.getResources(), getIconRightColorRes(), context.getTheme()));
            }

            if (getIconRightWidth() != -1 && getIconRightHeight() != -1){
                holder.iconRight.getLayoutParams().width = getIconRightWidth();
                holder.iconRight.getLayoutParams().height = getIconLeftHeight();
            }
        } else holder.iconRight.setVisibility(View.GONE);

        // Configure divider
        if (isDividerEnabled()){
            holder.divider.setVisibility(View.VISIBLE);
            if (getDividerColorRes() > 0){
                holder.divider.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), getDividerColorRes(), context.getTheme()));
            }
        } else holder.divider.setVisibility(View.GONE);

        // Configure enabled
        holder.cardView.setEnabled(isEnabled());
        holder.title.setEnabled(isEnabled());
        holder.description.setEnabled(isEnabled());
        holder.booleanValue.setEnabled(isEnabled());
        holder.value.setEnabled(isEnabled());
        holder.content.setEnabled(isEnabled());
        holder.iconLeft.setEnabled(isEnabled());
        holder.iconRight.setEnabled(isEnabled());
    }

    @Override
    public void unbindView(BodyMenuItemViewHolder holder) {

    }

    public static class BodyMenuItemViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private ImageView iconLeft;
        private TextView title;
        private TextView description;
        private TextView value;
        private Switch booleanValue;
        private ImageView iconRight;
        private TextView content;
        private TextView divider;

        public BodyMenuItemViewHolder(View v) {
            super(v);
            this.cardView = (CardView) v.findViewById(R.id.mm_item_body_cardview);
            this.iconLeft = (ImageView) v.findViewById(R.id.mm_item_body_icon_left);
            this.title = (TextView) v.findViewById(R.id.mm_item_body_title);
            this.description = (TextView) v.findViewById(R.id.mm_item_body_description);
            this.value = (TextView) v.findViewById(R.id.mm_item_body_value);
            this.booleanValue = (Switch) v.findViewById(R.id.mm_item_body_switch);
            this.iconRight = (ImageView) v.findViewById(R.id.mm_item_body_icon_right);
            this.content = (TextView) v.findViewById(R.id.mm_item_body_content);
            this.divider = (TextView) v.findViewById(R.id.mm_item_body_divider);
        }
    }
}
