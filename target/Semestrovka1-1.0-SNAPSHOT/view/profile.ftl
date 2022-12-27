<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Профиль</title>

    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/menu.css" rel="stylesheet">
    <link href="/resources/css/profile.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <#include "menu.ftl">

    <div class="center-content">
        <div class="container">
            <div class="title">Профиль</div>
            <div id="profile" class="white-container">

                <#if user.avatarId??>
                    <img height="300" width="300" class="user-fileInfo" alt="IMAGE" src="/files/${user.avatarId}"/>
                <#else>
                    <img class="user-fileInfo" alt="IMAGE" src="/no-avatar.png"/>
                </#if>

                <div class="change-avatar-button">
                    <a href = "http://localhost:8080/file-upload">Change avatar</a>
                </div>

                <div class="user-info-text">
                    <div class="user-info">${user.firstName}</div>
                    <div class="user-info">${user.lastName}</div>
                    <div class="user-info">${user.email}</div>
                    <div class="user-info">${user.phoneNumber}</div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>