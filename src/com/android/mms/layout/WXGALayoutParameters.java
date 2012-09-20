/*
 * Copyright (C) 2008 Esmertec AG.
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.mms.layout;

import android.content.Context;
import android.util.Log;

public class WXGALayoutParameters implements LayoutParameters {
    private static final String TAG = "WXGALayoutParameters";
    private static final boolean DEBUG = false;
    private static final boolean LOCAL_LOGV = false;

    private int mType = -2;

    private static int mImageHeightLandscape;
    private static int mTextHeightLandscape;
    private static int mImageHeightPortrait;
    private static int mTextHeightPortrait;
    private static int mMaxHeight;
    private static int mMaxWidth;

    public WXGALayoutParameters(Context context, int type) {
        if ((type != WXGA_LANDSCAPE) && (type != WXGA_PORTRAIT)) {
            throw new IllegalArgumentException(
                    "Bad layout type detected: " + type);
        }

        if (LOCAL_LOGV) {
            Log.v(TAG, "WXGALayoutParameters.<init>(" + type + ").");
        }
        mType = type;

        float scale = context.getResources().getDisplayMetrics().density;
        mMaxWidth = (int) (context.getResources().getConfiguration().screenWidthDp * scale + 0.5f);
        mMaxHeight =
            (int) (context.getResources().getConfiguration().screenHeightDp * scale + 0.5f);

        mImageHeightLandscape = (int) (mMaxHeight * .90f);
        mTextHeightLandscape = (int) (mMaxHeight * .10f);
        mImageHeightPortrait = (int) (mMaxWidth * .90f);
        mTextHeightPortrait = (int) (mMaxWidth * .10f);

        if (LOCAL_LOGV) {
            Log.v(TAG, "WXGALayoutParameters mMaxWidth: " + mMaxWidth +
                    " mMaxHeight: " + mMaxHeight +
                    " mImageHeightLandscape: " + mImageHeightLandscape +
                    " mTextHeightLandscape: " + mTextHeightLandscape +
                    " mImageHeightPortrait: " + mImageHeightPortrait +
                    " mTextHeightPortrait: " + mTextHeightPortrait);
        }

    }

    public int getWidth() {
        return mType == WXGA_LANDSCAPE ? mMaxWidth
                                       : mMaxHeight;
    }

    public int getHeight() {
        return mType == WXGA_LANDSCAPE ? mMaxHeight
                                       : mMaxWidth;
    }

    public int getImageHeight() {
        return mType == WXGA_LANDSCAPE ? mImageHeightLandscape
                                       : mImageHeightPortrait;
    }

    public int getTextHeight() {
        return mType == WXGA_LANDSCAPE ? mTextHeightLandscape
                                       : mTextHeightPortrait;
    }

    public int getType() {
        return mType;
    }

    public String getTypeDescription() {
        return mType == WXGA_LANDSCAPE ? "WXGA-L" : "WXGA-P";
    }
}
