#!/bin/bash
NDK=/home/ubuntu/桌面/Android/NDK/android-ndk-r10e
SYSROOT=$NDK/platforms/android-9/arch-arm/
TOOLCHAIN=$NDK/toolchains/arm-linux-androideabi-4.8/prebuilt/linux-x86_64
function build_android
{
./configure \
--prefix=/home/ubuntu/桌面/Android/FFmpeg/ffmpeg-3.2/android/arm \
--enable-neon \
--enable-hwaccel=h264_vaapi \
--enable-hwaccel=h264_vaapi \
--enable-hwaccel=h264_dxva2 \
--enable-hwaccel=mpeg4_vaapi \
--enable-hwaccels \
--enable-shared \
--enable-jni \
--enable-mediacodec \
--disable-static \
--disable-doc \
--enable-ffmpeg \
--disable-ffplay \
--disable-ffprobe \
--disable-ffserver \
--enable-avdevice \
--disable-doc \
--disable-symver \
--cross-prefix=$TOOLCHAIN/bin/arm-linux-androideabi- \
--target-os=linux \
--arch=arm \
--enable-cross-compile \
--sysroot=$SYSROOT \
--extra-cflags="-Os -fpic $ADDI_CFLAGS" \
--extra-ldflags="$ADDI_LDFLAGS" \
$ADDITIONAL_CONFIGURE_FLAG
make clean
make -j4
make install
}
ADDI_CFLAGS="-marm"
build_android
