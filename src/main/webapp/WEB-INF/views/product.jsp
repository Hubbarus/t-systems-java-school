<!doctype html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Your Favorite Online Shop
    </title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="canonical" href="https://getbootstrap.ru/docs/4.5/examples/album/">
    <link href="/docs/4.5/dist/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="/docs/4.5/vendor/imazin/imazin.css">
    <link rel="apple-touch-icon" href="/docs/4.5/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="/docs/4.5/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="/docs/4.5/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="/docs/4.5/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="/docs/4.5/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
    <link rel="icon" href="/docs/4.5/assets/img/favicons/favicon.ico">
    <meta name="msapplication-config" content="/docs/4.5/assets/img/favicons/browserconfig.xml">
    <meta name="theme-color" content="#563d7c">
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <link href="album.css" rel="stylesheet">
</head>

<body>
<!--  NAVIGATION-->
<header th:insert="blocks/basic :: nav"></header>

<!-- MAIN CONTAINER-->
<main role="main">
    <div>
        <p th:text="${name}"></p><br>
        <p th:text="${description}"></p><br>
        <p th:text="${product}"></p>
    </div>
</main>

<footer th:insert="blocks/basic :: footer"></footer>
</body>
</html>
