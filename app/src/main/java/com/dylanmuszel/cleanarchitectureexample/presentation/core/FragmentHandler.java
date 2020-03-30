package com.dylanmuszel.cleanarchitectureexample.presentation.core;

import javax.inject.Inject;

public class FragmentHandler<T extends BasePresenter> {

    private final T mPresenter;

    @Inject
    FragmentHandler(final T presenter) {
        mPresenter = presenter;
    }

    void onCreate(final Object view) {
        mPresenter.onCreate(view);
    }

    void onDestroy() {
        mPresenter.onDestroy();
    }

    T getPresenter() {
        return mPresenter;
    }
}
