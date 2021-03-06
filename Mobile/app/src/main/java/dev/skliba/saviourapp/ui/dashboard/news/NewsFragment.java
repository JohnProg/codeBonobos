package dev.skliba.saviourapp.ui.dashboard.news;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import dev.skliba.saviourapp.R;
import dev.skliba.saviourapp.data.models.response.NewsResponse;
import dev.skliba.saviourapp.di.MvpFactory;
import dev.skliba.saviourapp.ui.shared.BaseFragment;
import dev.skliba.saviourapp.ui.shared.BaseMvp;

public class NewsFragment extends BaseFragment implements NewsMvp.View {

    @BindView(R.id.emptyView)
    LinearLayout emptyView;

    @BindView(R.id.webview)
    WebView webview;

    private NewsMvp.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected BaseMvp.Presenter providePresenter() {
        presenter = MvpFactory.providePresenter(this);
        return presenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    private void init() {
        webview.loadUrl("http://www.gss.hr/");
    }

    @Override
    public void initUi(List<NewsResponse> news) {

    }
}
