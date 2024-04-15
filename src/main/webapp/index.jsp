<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Home</h1>

    <!-- Форма для поиска локаций -->
    <form action="search" method="post">
        <div class="mb-3">
            <label for="locationSearch" class="form-label">Search Locations</label>
            <input type="text" class="form-control" id="locationSearch" name="location" placeholder="Enter location name">
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

    <!-- Список локаций пользователя -->
    <h2>Locations</h2>
    <ul>
        <li>Location 1</li>
        <li>Location 2</li>
        <li>Location 3</li>
    </ul>

    <!-- Кнопка логаута, если пользователь авторизован -->
    <a href="#" class="btn btn-danger">Logout</a>
</div>
</body>
</html>
