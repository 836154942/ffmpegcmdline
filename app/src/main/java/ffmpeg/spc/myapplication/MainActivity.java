package ffmpeg.spc.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {
    public ProgressDialog show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String path = Environment.getExternalStorageDirectory().getPath();

        findViewById(R.id.btnCutAudio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //原命令 ffmpeg -i input.mp3 -ss hh:mm:ss -t hh:mm:ss -acodec copy output.mp3
                //flac有点问题,  支持mp3 wma m4a
                String cmd = "ffmpeg -i " + path + "/test.mp3" + " -ss 00:01:40 -t 00:02:30 -acodec copy " + path + "/test_out.mp3";
                toExec(cmd);
            }
        });
        findViewById(R.id.btnConvertVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //原命令  ffmpeg -i "xxx.mp4" -y -ab 32 -ar 22050 -qscale 10 -s 640*480 -r 15 xxx_out.flv
//                -i 是 要转换文件名
//                -y是 覆盖输出文件
//                        -ab 是 音频数据流， 如 128 64
//                        -ar 是 声音的频率 22050 基本都是这个。
//                -qscale 是视频输出质量，后边的值越小质量越高，但是输出文件就越“肥”
//                -s 是输出 文件的尺寸大小！
//                -r 是 播放侦数。

                String cmd = "ffmpeg -i " + path + "/test.mp4" + " -y -ab 128 -ar 22050 -qscale 8 -s 1080*720 -r 25 " + path + "/test_out.flv";
                toExec(cmd);

            }
        });



    }

    private void toExec(String cmd) {
        show = ProgressDialog.show(MainActivity.this, null, "执行中...", true);
        //转换为数组
        String[] cmds = cmd.split(" ");
        FFmpegCmd.exec(cmds, new FFmpegCmd.OnExecListener() {
            @Override
            public void onExecuted(final int ret) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "执行完成=" + ret, Toast.LENGTH_SHORT).show();
                        show.dismiss();
                    }
                });
            }
        });
    }
}

