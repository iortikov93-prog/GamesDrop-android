GAMES DROP — ЛОИҲАИ ANDROID (APK)

Ин лоиҳа барномаи Android-и Games Drop мебошад. Барнома сайти https://gamesdrop.org/-ро дар WebView мекушояд; базаи маълумот, фармоишҳо ва амалиёт дар сервери сайт мемонанд.

СОХТАНИ APK БО GITHUB ACTIONS (АЗ ТЕЛЕФОН ҲАМ МЕШАВАД):
1. Ба github.com дароед ва repository-и нав созед (масалан GamesDropAndroid). Public ё Private — интихоби худатон.
2. Ҳамаи файлҳои дохили папкаи GamesDropAndroid-ро ба решаи repository бор кунед. Папкаи .github/workflows/build-apk.yml низ бояд нигоҳ дошта шавад.
3. Пас аз боркунӣ ба ҷадвали Actions дар repository гузаред.
4. Workflow-и "Build Games Drop APK"-ро кушоед ва "Run workflow"-ро пахш кунед. Агар workflow худкор оғоз шуда бошад, интизор шавед.
5. Вақте ки иҷрои workflow сабз шуд, ба натиҷаи он дар поёни саҳифа рафта, аз Artifacts файли "GamesDrop-APK"-ро зеркашӣ кунед.
6. ZIP-и artifact-ро дар телефон кушоед. Дар дохилаш app-debug.apk ҳаст. Онро насб кунед.

ТАНЗИМОТ:
- Номи барнома: Games Drop
- Package ID: com.gamesdrop.app
- Суроға: https://gamesdrop.org/
- HTTPS ҳатмист.

ЭЗОҲҲО:
- Ин манбаи лоиҳа аст, на APK-и тайёр.
- GitHub Actions бо Java 17, Gradle 8.9 ва Android SDK 35 APK-и debug месозад.
- APK-и debug барои санҷиш аст. Барои Google Play APK/AAB-и release бо signing key лозим мешавад.
- Google OAuth дар WebView маҳдудият дошта метавонад; лоиҳа Google Login-ро дар браузери беруна мекушояд. Баргашти OAuth-ро дар худи сайт санҷед.
- Пеш аз нашр сиёсати махфият ва талаботи Google Play-ро омода кунед.
