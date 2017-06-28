package com.whatscover.android.helpers;

import android.content.Context;
import android.content.Intent;

import com.whatscover.android.constant.Constant;
import com.whatscover.android.utility.A;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Isa Andi on 16/05/2017.
 */

public class ActivityBuilder {
    private Class a;
    private List data;
    private boolean finishCurrent;
    private Context ctx;

    public static ActivityBuilder getBuilder() {
        ActivityBuilder builder = new ActivityBuilder();
        return builder;
    }

    public ActivityBuilder withContext(Context ctx) {
        this.ctx=ctx;
        return this;
    }

    public ActivityBuilder withClass(Class a) {
        this.a=a;
        return this;
    }

    public ActivityBuilder withParam(List data) {
        this.data=data;
        return this;
    }

    public ActivityBuilder withFinishCurrent(boolean finishCurrent) {
        this.finishCurrent=finishCurrent;
        return this;
    }

    public Intent build() {
        Intent i = new Intent(ctx, a);
        if(data!=null)
            i.putExtra(Constant.DATA, (Serializable) data);

        if (finishCurrent)
            A.goOtherActivityFinish(ctx, a);
        else
            A.goOtherActivity(ctx, a);

        return i;
    }
}
