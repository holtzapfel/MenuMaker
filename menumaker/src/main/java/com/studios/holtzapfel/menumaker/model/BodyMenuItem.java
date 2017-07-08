package com.studios.holtzapfel.menumaker.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

    public static final int ICON_ARROW_RIGHT = 50;
    public static final int ICON_ARROW_LEFT = 51;
    public static final int ICON_ARROW_UP = 52;
    public static final int ICON_ARROW_DOWN = 53;
    public static final int ICON_OPEN_IN_NEW = 54;
    public static final int ICON_OPEN_IN_BROWSER = 55;


    private String mTitle;
    private int mTitleColorRes = -1;

    private String mDescription;
    private String mValue;

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

    private boolean mBooleanValue = false;
    private boolean isSwitchUsed = false;
    private String mContent;

    private int mIconLeftRes = -1;
    private Drawable mIconLeft;
    private boolean isIconLeftVisible = true;
    private int mIconLeftColorRes = -1;
    private int mIconLeftHeight = -1;
    private int mIconLeftWidth = -1;

    private int mIconRightRes = -1;
    private Drawable mIconRight;
    private boolean isIconRightVisible = true;
    private int mIconRightColorRes = -1;
    private int mIconRightHeight = -1;
    private int mIconRightWidth = -1;

    private boolean isDividerEnabled = true;
    private int mDividerColorRes = -1;

    public BodyMenuItem(int id){
        this.mID = id;
    }

    @Override
    public BodyMenuItem withTitle(String title) {
        this.mTitle = title;
        return this;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public BodyMenuItem withTitleTextColor(int colorRes) {
        this.mTitleColorRes = colorRes;
        return this;
    }

    @Override
    public int getTitleTextColorRes() {
        return mTitleColorRes;
    }

    @Override
    public BodyMenuItem withDescription(String description) {
        this.mDescription = description;
        return this;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public BodyMenuItem withValue(String value) {
        this.mValue = value;
        return this;
    }

    @Override
    public String getValue() {
        return mValue;
    }

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

    @Override
    public BodyMenuItem withContent(String content) {
        this.mContent = content;
        return this;
    }

    @Override
    public String getContent() {
        return mContent;
    }

    @Override
    public BodyMenuItem withIconLeft(int iconRes) {
        switch (iconRes){
            case ICON_ARROW_RIGHT:
                this.mIconLeftRes = R.drawable.ic_arrow_right;
                withIconLeftSize(75, 75);
                return this;
            case ICON_ARROW_LEFT:
                this.mIconLeftRes = R.drawable.ic_arrow_left;
                withIconLeftSize(75, 75);
                return this;
            case ICON_ARROW_UP:
                this.mIconLeftRes = R.drawable.ic_arrow_up;
                withIconLeftSize(75, 75);
                return this;
            case ICON_ARROW_DOWN:
                this.mIconLeftRes = R.drawable.ic_arrow_down;
                withIconLeftSize(75, 75);
                return this;
            case ICON_OPEN_IN_NEW:
                this.mIconLeftRes = R.drawable.ic_action_open_in_new;
                withIconLeftSize(75, 75);
                return this;
            case ICON_OPEN_IN_BROWSER:
                this.mIconLeftRes = R.drawable.ic_action_open_in_browser;
                withIconLeftSize(75, 75);
                return this;
        }
        this.mIconLeftRes = iconRes;
        return this;
    }

    @Override
    public BodyMenuItem withIconLeft(Drawable icon) {
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

    @Override
    public BodyMenuItem withIconRight(int iconRes) {
        switch (iconRes){
            case ICON_ARROW_RIGHT:
                this.mIconRightRes = R.drawable.ic_arrow_right;
                withIconRightSize(75,75);
                return this;
            case ICON_ARROW_LEFT:
                this.mIconRightRes = R.drawable.ic_arrow_left;
                withIconRightSize(75, 75);
                return this;
            case ICON_ARROW_UP:
                this.mIconRightRes = R.drawable.ic_arrow_up;
                withIconRightSize(75, 75);
                return this;
            case ICON_ARROW_DOWN:
                this.mIconRightRes = R.drawable.ic_arrow_down;
                withIconRightSize(75, 75);
                return this;
            case ICON_OPEN_IN_NEW:
                this.mIconRightRes = R.drawable.ic_action_open_in_new;
                withIconRightSize(75,75);
                return this;
            case ICON_OPEN_IN_BROWSER:
                this.mIconRightRes = R.drawable.ic_action_open_in_browser;
                withIconRightSize(75,75);
                return this;
        }
        this.mIconRightRes = iconRes;
        return this;
    }

    @Override
    public BodyMenuItem withIconRight(Drawable icon) {
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

    @Override
    public BodyMenuItem withIconLeftVisible(boolean isVisible) {
        this.isIconLeftVisible = isVisible;
        return this;
    }

    @Override
    public boolean isIconLeftVisible() {
        return isIconLeftVisible;
    }

    @Override
    public BodyMenuItem withIconRightVisible(boolean isVisible) {
        this.isIconRightVisible = isVisible;
        return this;
    }

    @Override
    public boolean isIconRightVisible() {
        return isIconRightVisible;
    }

    @Override
    public BodyMenuItem withIconLeftColor(int colorRes) {
        this.mIconLeftColorRes = colorRes;
        return this;
    }

    @Override
    public int getIconLeftColorRes() {
        return mIconLeftColorRes;
    }

    @Override
    public BodyMenuItem withIconRightColor(int colorRes) {
        this.mIconRightColorRes = colorRes;
        return this;
    }

    @Override
    public int getIconRightColorRes() {
        return mIconRightColorRes;
    }

    public BodyMenuItem withIconLeftSize(int width, int height){
        this.mIconLeftWidth = width;
        this.mIconLeftHeight = height;
        return this;
    }

    public int getIconLeftWidth(){
        return mIconLeftWidth;
    }

    public int getIconLeftHeight(){
        return mIconLeftHeight;
    }

    public BodyMenuItem withIconRightSize(int width, int height){
        this.mIconRightWidth = width;
        this.mIconRightHeight = height;
        return this;
    }

    public int getIconRightWidth(){
        return mIconRightWidth;
    }

    public int getIconRightHeight(){
        return mIconRightHeight;
    }

    @Override
    public BodyMenuItem withDividerEnabled(boolean isEnabled) {
        this.isDividerEnabled = isEnabled;
        return this;
    }

    @Override
    public boolean isDividerEnabled() {
        return isDividerEnabled;
    }

    @Override
    public BodyMenuItem withDividerColor(int colorRes) {
        this.mDividerColorRes = colorRes;
        return this;
    }

    @Override
    public int getDividerColorRes() {
        return mDividerColorRes;
    }

    public interface OnVerifyInputListener{
        boolean onVerifyInput(CharSequence input);
        void afterVerification(boolean inputVerified);
    }

    public BodyMenuItem withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle) {
        this.withValue(value);
        this.isValueEditable = true;
        this.mEditableHint = inputHint;
        this.prefillWithValue = prefillInputWithItemValue;
        this.allowEmptyInput = allowEmptyInput;
        this.mEditableTitle = dialogTitle;
        return this;
    }

    public BodyMenuItem withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, @Nullable OnVerifyInputListener verifyInputListener) {
        this.withValueEditable(value, inputHint, prefillInputWithItemValue, allowEmptyInput, dialogTitle);
        this.mVerifyInputListener = verifyInputListener;
        return this;
    }

    public BodyMenuItem withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, int inputType) {
        withValueEditable(value, inputHint, prefillInputWithItemValue, allowEmptyInput, dialogTitle);
        this.mEditableInputType = inputType;
        isInputTypePassword = inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD;
        return this;
    }

    public BodyMenuItem withValueEditable(@Nullable String value, @Nullable CharSequence inputHint, boolean prefillInputWithItemValue, boolean allowEmptyInput, @Nullable CharSequence dialogTitle, int inputType, @Nullable OnVerifyInputListener verifyInputListener) {
        withValueEditable(value, inputHint, prefillInputWithItemValue, allowEmptyInput, dialogTitle, inputType);
        this.mVerifyInputListener = verifyInputListener;
        return this;
    }

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

    public boolean isValueEditable() {
        return isValueEditable;
    }

    private CharSequence getPrefillValue(){
        if (prefillWithValue){
            return mValue;
        } else return null;
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
                    } else if (isValueEditable()){
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
            if (isInputTypePassword) {
                holder.value.setTransformationMethod(new PasswordTransformationMethod());
            }
        } else holder.value.setVisibility(View.GONE);

        // Configure content
        if (getContent() != null){
            holder.content.setVisibility(View.VISIBLE);
            holder.content.setText(Master.fromHtml(getContent()));
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
            if (getDividerColorRes() != -1){
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
