/*
 * Copyright (C) 2016 The Android Open Source Project
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

package android.support.v7.widget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;

import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.support.v7.widget.BaseWrapContentWithAspectRatioTest.*;

@RunWith(JUnit4.class)
public class StaggeredGridLayoutManagerWrapContentTest extends BaseWrapContentTest {

    public StaggeredGridLayoutManagerWrapContentTest() {
        super(new WrapContentConfig(false, false));
    }

    @Test
    public void testSimple() throws Throwable {
        TestedFrameLayout.FullControlLayoutParams lp =
                mWrapContentConfig.toLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        WrapContentAdapter adapter = new WrapContentAdapter(
                new MeasureBehavior(10, 10, WRAP_CONTENT, WRAP_CONTENT),
                new MeasureBehavior(10, 15, WRAP_CONTENT, WRAP_CONTENT),
                new MeasureBehavior(10, 20, WRAP_CONTENT, WRAP_CONTENT),
                new MeasureBehavior(20, 10, WRAP_CONTENT, WRAP_CONTENT)
        );
        Rect[] expected = new Rect[] {
                new Rect(0, 0, 10, 10),
                new Rect(20, 0, 30, 15),
                new Rect(40, 0, 50, 20),
                new Rect(0, 10, 20, 20)
        };
        layoutAndCheck(lp, adapter, expected, 60, 20);
    }

    @Test
    public void testUnspecifiedWidth() throws Throwable {
        TestedFrameLayout.FullControlLayoutParams lp =
                mWrapContentConfig.toLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lp.wSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        WrapContentAdapter adapter = new WrapContentAdapter(
                new MeasureBehavior(2000, 10, WRAP_CONTENT, WRAP_CONTENT),
                new MeasureBehavior(500, 15, WRAP_CONTENT, WRAP_CONTENT),
                new MeasureBehavior(400, 20, WRAP_CONTENT, WRAP_CONTENT),
                new MeasureBehavior(50, 10, MATCH_PARENT, WRAP_CONTENT)
        );
        Rect[] expected = new Rect[] {
                new Rect(0, 0, 2000, 10),
                new Rect(2000, 0, 2500, 15),
                new Rect(4000, 0, 4400, 20),
                new Rect(0, 10, 2000, 20)
        };
        layoutAndCheck(lp, adapter, expected, 6000, 20);
    }

    @Test
    public void testUnspecifiedHeight() throws Throwable {
        TestedFrameLayout.FullControlLayoutParams lp =
                mWrapContentConfig.toLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lp.hSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        WrapContentAdapter adapter = new WrapContentAdapter(
                new MeasureBehavior(10, 4000, WRAP_CONTENT, WRAP_CONTENT),
                new MeasureBehavior(10, 5500, WRAP_CONTENT, WRAP_CONTENT),
                new MeasureBehavior(10, 3000, WRAP_CONTENT, WRAP_CONTENT),
                new MeasureBehavior(20, 100, WRAP_CONTENT, WRAP_CONTENT)
        );
        Rect[] expected = new Rect[] {
                new Rect(0, 0, 10, 4000),
                new Rect(20, 0, 30, 5500),
                new Rect(40, 0, 50, 3000),
                new Rect(40, 3000, 60, 3100)
        };
        layoutAndCheck(lp, adapter, expected, 60, 5500);
    }

    @Override
    RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(3, VERTICAL);
    }

    @Override
    protected int getVerticalGravity(RecyclerView.LayoutManager layoutManager) {
        return Gravity.TOP;
    }

    @Override
    protected int getHorizontalGravity(RecyclerView.LayoutManager layoutManager) {
        return Gravity.LEFT;
    }
}
