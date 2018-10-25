package org.jetbrains.kotlinconf.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import org.jetbrains.kotlinconf.R

/**
 * @Data 2018/7/2 下午1:58
 * @author wjt
 * @Description 带一键删除的EditText。
 * @version 1.0
 */
class ClearEditText constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = android.R.attr.editTextStyle) : android.support.v7.widget.AppCompatEditText(context, attrs, defStyle), View.OnFocusChangeListener, TextWatcher {
    /**
     * 删除按钮的引用
     */
    private var mClearDrawable: Drawable? = null
    /**
     * 控件是否有焦点
     */
    private var hasFocus: Boolean = false

    private var mClearIconHeightDef = 16f
    private var mClearIconHeight = 0
    private var mClearIconWidthDef = 16f
    private var mClearIconWidth = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, android.support.v7.appcompat.R.attr.editTextStyle)

    init {
        init(context, attrs, defStyle)
    }


    private fun init(context: Context, attrs: AttributeSet?, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClearEditText, defStyle, 0)
        val clearDrawable = typedArray.getDrawable(R.styleable.ClearEditText_clearEditTextClearIcon)

        mClearIconHeight = typedArray.getDimensionPixelSize(R.styleable.ClearEditText_clearEditTextClearIconHeight, dip2px(context, mClearIconHeightDef))
        mClearIconWidth = typedArray.getDimensionPixelSize(R.styleable.ClearEditText_clearEditTextClearIconWidth, dip2px(context, mClearIconWidthDef))

        typedArray.recycle()

        initClearIconState(clearDrawable)

        initListener()
    }

    private fun initClearIconState(clearDrawable: Drawable?) {
        /**
         * 1.DrawableRight 不为空，直接使用DrawableRight
         * 2.DrawableRight 为空，clearDrawable不为空，使用clearDrawable
         * 3.DrawableRight,clearDrawable都为空，使用默认。
         */
        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = compoundDrawables[2]
        mClearDrawable = mClearDrawable ?: clearDrawable ?: resources.getDrawable(R.drawable.signup_ic_close_rounded)

        setDrawableBounds(mClearDrawable!!,mClearIconWidth,mClearIconHeight)
        //默认设置隐藏图标
        setClearIconVisible(false)
    }

    private fun setDrawableBounds(drawable: Drawable, width: Int, height: Int) {
        val scale = drawable.intrinsicHeight.toDouble() / drawable.intrinsicWidth.toDouble()
        drawable.setBounds(0, 0, width, height)
        val bounds = drawable.bounds
        if (bounds.right != 0 || bounds.bottom != 0) {
            if (bounds.right == 0) {
                bounds.right = (bounds.bottom / scale).toInt()
                drawable.bounds = bounds
            }
            if (bounds.bottom == 0) {
                bounds.bottom = bounds.right * scale.toInt()
                drawable.bounds = bounds
            }
        }
    }

    private fun initListener() {
        //设置焦点改变的监听
        onFocusChangeListener = this
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this)
    }


    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            if (compoundDrawables[2] != null) {

                val touchable = event.x > width - totalPaddingRight && event.x < width - paddingRight

                if (touchable) {
                    this.setText("")
                }
            }
        }

        return super.onTouchEvent(event)
    }

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    override fun onFocusChange(v: View, hasFocus: Boolean) {
        this.hasFocus = hasFocus
        if (hasFocus) {
            setClearIconVisible(text.isNotEmpty())
        } else {
            setClearIconVisible(false)
        }
    }


    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     * @param visible 设置右侧清理Icon显示隐藏
     */
    fun setClearIconVisible(visible: Boolean) {
        val right = if (visible) mClearDrawable else null
        setCompoundDrawables(compoundDrawables[0],
                compoundDrawables[1], right, compoundDrawables[3])
    }


    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    override fun onTextChanged(s: CharSequence, start: Int, count: Int,
                               after: Int) {
        if (hasFocus) {
            setClearIconVisible(s.isNotEmpty())
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                   after: Int) {

    }

    override fun afterTextChanged(s: Editable) {

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

}
