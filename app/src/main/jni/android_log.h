
#ifdef ANDROID
#include <android/log.h>
#define LOGE(format, ...)  __android_log_print(ANDROID_LOG_ERROR, "ffmpeg_toshiba", format, ##__VA_ARGS__)
#define LOGI(format, ...)  __android_log_print(ANDROID_LOG_INFO,  "ffmpeg_toshiba", format, ##__VA_ARGS__)
#else
#define LOGE(format, ...)  printf("ffmpeg_toshiba" format "\n", ##__VA_ARGS__)
#define LOGI(format, ...)  printf("ffmpeg_toshiba" format "\n", ##__VA_ARGS__)
#endif
