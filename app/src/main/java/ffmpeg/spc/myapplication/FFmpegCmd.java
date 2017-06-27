package ffmpeg.spc.myapplication;

/**
 * Created by spc on 2017/6/27.
 */

public class FFmpegCmd {
    static {
        System.loadLibrary("avutil-55");
        System.loadLibrary("avcodec-57");
        System.loadLibrary("swresample-2");
        System.loadLibrary("avformat-57");
        System.loadLibrary("swscale-4");
        System.loadLibrary("avfilter-6");
        System.loadLibrary("avdevice-57");
        System.loadLibrary("myffmpeg");
    }

    private static OnExecListener listener;

    /**
     * 调用底层执行
     * @param argc
     * @param argv
     * @return
     */
    public static native int exec(int argc, String[] argv);

    public static void onExecuted(int ret) {
        if (listener != null) {
            listener.onExecuted(ret);
        }
    }

    /**
     * 执行ffmoeg命令
     * @param cmds
     * @param listener
     */
    public static void exec(String[] cmds, OnExecListener listener) {
        FFmpegCmd.listener = listener;
        exec(cmds.length, cmds);
    }

    /**
     * 执行完成/错误 时的回调接口
     */
    public interface OnExecListener {
        void onExecuted(int ret);
    }
}
