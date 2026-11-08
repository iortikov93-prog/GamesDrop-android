package com.gamesdrop.app;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String HOME = "https://gamesdrop.org/";
    private final int BG = Color.rgb(10, 13, 25);
    private final int CARD = Color.rgb(22, 28, 47);
    private final int MUTED = Color.rgb(157, 168, 190);
    private final int WHITE = Color.WHITE;
    private WebView webView;
    private LinearLayout root;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);
        showDashboard();
    }

    private GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable(); d.setColor(color); d.setCornerRadius(dp(radius)); return d;
    }
    private int dp(float v) { return (int)(v * getResources().getDisplayMetrics().density + 0.5f); }
    private TextView label(String text, int size, int color, boolean bold) {
        TextView t = new TextView(this); t.setText(text); t.setTextSize(size); t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD); return t;
    }
    private LinearLayout column() { LinearLayout l = new LinearLayout(this); l.setOrientation(LinearLayout.VERTICAL); return l; }
    private LinearLayout row() { LinearLayout l = new LinearLayout(this); l.setOrientation(LinearLayout.HORIZONTAL); l.setGravity(Gravity.CENTER_VERTICAL); return l; }
    private void pad(View v, int a, int b, int c, int d) { v.setPadding(dp(a),dp(b),dp(c),dp(d)); }

    private void showDashboard() {
        root = column(); root.setBackgroundColor(BG);
        ScrollView scroll = new ScrollView(this); scroll.setFillViewport(true);
        LinearLayout page = column(); pad(page,20,18,20,28);

        LinearLayout top = row();
        TextView logo = label("GD",18,WHITE,true); logo.setGravity(Gravity.CENTER); logo.setBackground(shape(0xFF7657FF,16));
        top.addView(logo,new LinearLayout.LayoutParams(dp(48),dp(48)));
        LinearLayout titleBox = column(); pad(titleBox,12,0,0,0);
        titleBox.addView(label("GAMES DROP",18,WHITE,true));
        titleBox.addView(label("GAME TOP-UP STORE",10,MUTED,true));
        top.addView(titleBox,new LinearLayout.LayoutParams(0,-2,1));
        TextView profile = label("◉",23,WHITE,true); profile.setGravity(Gravity.CENTER); profile.setBackground(shape(CARD,30));
        top.addView(profile,new LinearLayout.LayoutParams(dp(44),dp(44))); page.addView(top);

        TextView welcome = label("Бозиҳои дӯстдоштаатро\nпурқувваттар кун!",27,WHITE,true); pad(welcome,0,30,0,8); page.addView(welcome);
        TextView sub = label("Алмос ва хизматрасониҳои бозиро осон интихоб кун.",13,MUTED,false); page.addView(sub);

        LinearLayout hero = column(); hero.setBackground(new GradientDrawable(GradientDrawable.Orientation.TL_BR,new int[]{0xFF694BFF,0xFF302A78})); ((GradientDrawable) hero.getBackground()).setCornerRadius(dp(22)); pad(hero,20,20,20,20);
        TextView tag = label("GAMES DROP  •  TOP-UP",11,0xFFE5DEFF,true); hero.addView(tag);
        TextView heroTitle = label("Омодаӣ ба\nғалабаи нав?",24,WHITE,true); pad(heroTitle,0,14,0,14); hero.addView(heroTitle);
        TextView go = label("КУШОДАНИ МАҒОЗА  ➜",13,0xFF251C55,true); go.setGravity(Gravity.CENTER); go.setBackground(shape(WHITE,14)); pad(go,14,15,14,15);
        hero.addView(go,new LinearLayout.LayoutParams(-1,-2)); go.setOnClickListener(v -> openStore());
        LinearLayout.LayoutParams hp = new LinearLayout.LayoutParams(-1,-2); hp.topMargin=dp(24); page.addView(hero,hp);

        TextView section = label("Бозиҳои маъмул",19,WHITE,true); pad(section,0,28,0,14); page.addView(section);
        LinearLayout games = row();
        games.addView(gameCard("🔥","Free Fire","Алмос ва бастаҳо"),new LinearLayout.LayoutParams(0,-2,1));
        View gap = new View(this); games.addView(gap,new LinearLayout.LayoutParams(dp(12),1));
        games.addView(gameCard("⚔️","PUBG Mobile","UC ва бастаҳо"),new LinearLayout.LayoutParams(0,-2,1)); page.addView(games);

        TextView features = label("Хизматрасониҳо",19,WHITE,true); pad(features,0,26,0,12); page.addView(features);
        page.addView(infoCard("💎","Пур кардани баланс","Маълумоти пардохт ва пуркунӣ"));
        page.addView(infoCard("📦","Фармоишҳои ман","Пайгирии ҳолати фармоишҳо"));
        page.addView(infoCard("🎧","Дастгирӣ","Кӯмак ва саволҳои маъмул"));
        TextView foot = label("© Games Drop  •  Версияи 1.0",11,0xFF77819A,false); foot.setGravity(Gravity.CENTER); pad(foot,0,24,0,4); page.addView(foot);

        scroll.addView(page); root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));
        LinearLayout nav = row(); nav.setBackgroundColor(0xFF12182A); pad(nav,8,10,8,10);
        nav.addView(navItem("⌂","Асосӣ",true),new LinearLayout.LayoutParams(0,dp(48),1));
        nav.addView(navItem("▦","Мағоза",false),new LinearLayout.LayoutParams(0,dp(48),1));
        nav.addView(navItem("◷","Фармоиш",false),new LinearLayout.LayoutParams(0,dp(48),1));
        nav.addView(navItem("♙","Профил",false),new LinearLayout.LayoutParams(0,dp(48),1));
        root.addView(nav); setContentView(root);
    }

    private View gameCard(String icon, String name, String desc) {
        LinearLayout c = column(); c.setBackground(shape(CARD,18)); pad(c,14,16,12,16);
        TextView i = label(icon,28,WHITE,true); c.addView(i);
        TextView n = label(name,15,WHITE,true); pad(n,0,12,0,3); c.addView(n);
        c.addView(label(desc,11,MUTED,false));
        c.setOnClickListener(v -> openStore()); return c;
    }
    private View infoCard(String icon,String title,String desc) {
        LinearLayout c = row(); c.setBackground(shape(CARD,16)); pad(c,14,14,14,14);
        TextView i=label(icon,22,WHITE,true); i.setGravity(Gravity.CENTER); c.addView(i,new LinearLayout.LayoutParams(dp(42),dp(42)));
        LinearLayout tx=column(); pad(tx,12,0,0,0); tx.addView(label(title,14,WHITE,true)); tx.addView(label(desc,11,MUTED,false));
        c.addView(tx,new LinearLayout.LayoutParams(0,-2,1)); c.addView(label("›",25,MUTED,false));
        c.setOnClickListener(v -> openStore()); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.bottomMargin=dp(10); c.setLayoutParams(p); return c;
    }
    private View navItem(String icon,String name,boolean selected) {
        LinearLayout box=column(); box.setGravity(Gravity.CENTER); TextView a=label(icon,20,selected?0xFF9B88FF:MUTED,true); a.setGravity(Gravity.CENTER); box.addView(a);
        TextView b=label(name,10,selected?0xFFB9ACFF:MUTED,selected); b.setGravity(Gravity.CENTER); box.addView(b);
        box.setOnClickListener(v -> { if (!selected) openStore(); }); return box;
    }
    private void openStore() {
        root = column(); root.setBackgroundColor(BG);
        LinearLayout bar=row(); pad(bar,14,12,14,12);
        TextView back=label("‹  Бозгашт",16,WHITE,true); bar.addView(back); back.setOnClickListener(v -> showDashboard()); root.addView(bar);
        webView=new WebView(this); CookieManager.getInstance().setAcceptCookie(true); CookieManager.getInstance().setAcceptThirdPartyCookies(webView,true);
        webView.getSettings().setJavaScriptEnabled(true); webView.getSettings().setDomStorageEnabled(true); webView.getSettings().setDatabaseEnabled(true);
        webView.setWebViewClient(new WebViewClient()); webView.setWebChromeClient(new WebChromeClient());
        root.addView(webView,new LinearLayout.LayoutParams(-1,0,1)); setContentView(root); webView.loadUrl(HOME);
    }
    @Override public void onBackPressed() { if(webView!=null && webView.canGoBack()) webView.goBack(); else showDashboard(); }
    @Override protected void onDestroy() { if(webView!=null) webView.destroy(); super.onDestroy(); }
}
