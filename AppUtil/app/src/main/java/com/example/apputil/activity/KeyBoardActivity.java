package com.example.apputil.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.example.apputil.R;
import com.example.apputil.emoticonskeyboard.common.Constants;
import com.example.apputil.emoticonskeyboard.common.SimpleAppsGridView;
import com.example.apputil.emoticonskeyboard.common.SimpleCommonUtils;
import com.sj.emoji.EmojiBean;

import sj.keyboard.XhsEmoticonsKeyBoard;
import sj.keyboard.data.EmoticonEntity;
import sj.keyboard.interfaces.EmoticonClickListener;
import sj.keyboard.recorder.view.AudioRecordButton;
import sj.keyboard.recorder.view.MediaManager;
import sj.keyboard.utils.EmoticonsKeyboardUtils;
import sj.keyboard.widget.FuncLayout;

/**
 * Created by tujingwu on 2017/1/6.
 *
 * 表情键盘出处：https://github.com/w446108264/XhsEmoticonsKeyboard
 */
public class KeyBoardActivity extends AppCompatActivity implements FuncLayout.OnFuncKeyBoardListener{
    private XhsEmoticonsKeyBoard ekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        ekBar = (XhsEmoticonsKeyBoard) findViewById(R.id.ek_bar);
        initKeyBoard();
    }

    private void initKeyBoard() {
        SimpleAppsGridView simpleAppsGridView = new SimpleAppsGridView(this);
        //实例化Activity便于相机或图库返回数据
        //simpleAppsGridView.initData(allorNot);
        //simpleAppsGridView.initActivity(this, ChatTypeConfig.SAY);
        ekBar.addFuncView(simpleAppsGridView);//+号(更多)点击监听
        SimpleCommonUtils.initEmoticonsEditText(ekBar.getEtChat());//这个是编辑框能否显示表情
        ekBar.setAdapter(SimpleCommonUtils.getCommonAdapter(this, emoticonClickListener));
        // ekBar.addOnFuncKeyBoardListener(this);
     /*   ekBar.getEtChat().setOnSizeChangedListener(new EmoticonsEditText.OnSizeChangedListener() {
            @Override
            public void onSizeChanged(int w, int h, int oldw, int oldh) {
                scrollToBottom();
            }
        });*/
        ekBar.getBtnSend().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击发送按钮时发送编辑框内容
            }
        });
        //底部增加 和设置两个点击事件
       /* ekBar.getEmoticonsToolBarView().addFixedToolItemView(false, R.mipmap.ic_launcher, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleChatActivity.this, "ADD", Toast.LENGTH_SHORT).show();
            }
        });*/
       /* ekBar.getEmoticonsToolBarView().addToolItemView(R.mipmap.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleChatActivity.this, "SETTING", Toast.LENGTH_SHORT).show();
            }
        });*/

        //语音
        AudioRecordButton btnVoice = ekBar.getBtnVoice();
        btnVoice.setAudioFinishRecorderListener(new AudioRecordButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(float seconds, String filePath) {
                //录音完成后的操作
            }
        });
    }

    //表情键盘点击事件监听
    EmoticonClickListener emoticonClickListener = new EmoticonClickListener() {
        @Override
        public void onEmoticonClick(Object o, int actionType, boolean isDelBtn) {
            if (isDelBtn) {//按了删除按钮
                SimpleCommonUtils.delClick(ekBar.getEtChat());
            } else {
                if (o == null) {
                    return;
                }
                if (actionType == Constants.EMOTICON_CLICK_BIGIMAGE) {
                    /*if (o instanceof GifEmoticon) {
                        //这里监听用户点击表情键盘的某个静态图片时
                        //OnSendImage(((EmoticonEntity)o).getIconUri());
                        //postImg(((GifEmoticon) o).getPath(), emot);
                        postExpression(((GifEmoticon) o));
                    }*/
                } else {
                    String content = null;
                    if (o instanceof EmojiBean) {
                        content = ((EmojiBean) o).emoji;
                    } else if (o instanceof EmoticonEntity) {
                        content = ((EmoticonEntity) o).getContent();
                    }

                    if (TextUtils.isEmpty(content)) {
                        return;
                    }
                    int index = ekBar.getEtChat().getSelectionStart();
                    Editable editable = ekBar.getEtChat().getText();
                    editable.insert(index, content);
                }
            }
        }
    };


    //表情键盘事件
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (EmoticonsKeyboardUtils.isFullScreen(this)) {
            boolean isConsum = ekBar.dispatchKeyEventInFullScreen(event);
            return isConsum ? isConsum : super.dispatchKeyEvent(event);
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    protected void onPause() {
        MediaManager.pause();
        super.onPause();
        ekBar.reset();
    }

    @Override
    protected void onResume() {
        MediaManager.resume();
        super.onResume();
    }
    @Override
    public void OnFuncPop(int height) {

    }

    @Override
    public void OnFuncClose() {

    }
}
