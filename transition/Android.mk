# Copyright (C) 2016 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

LOCAL_PATH := $(call my-dir)

# Here is the final static library that apps can link against.
# Applications that use this library must specify
#
#   LOCAL_STATIC_ANDROID_LIBRARIES := \
#       android-support-transition \
#       android-support-v4
#
# in their makefiles to include the resources and their dependencies in their package.
include $(CLEAR_VARS)
LOCAL_USE_AAPT2 := true
LOCAL_MODULE := android-support-transition
LOCAL_SDK_VERSION := $(SUPPORT_CURRENT_SDK_VERSION)
LOCAL_SRC_FILES := \
    $(call all-java-files-under,base) \
    $(call all-java-files-under,api14) \
    $(call all-java-files-under,api18) \
    $(call all-java-files-under,api19) \
    $(call all-java-files-under,api21) \
    $(call all-java-files-under,src)
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
LOCAL_SHARED_ANDROID_LIBRARIES := \
    android-support-annotations \
    android-support-v4
LOCAL_JAR_EXCLUDE_FILES := none
LOCAL_JAVA_LANGUAGE_VERSION := 1.7
LOCAL_AAPT_FLAGS := --no-version-transitions --add-javadoc-annotation doconly
include $(BUILD_STATIC_JAVA_LIBRARY)
