<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Book</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
            color: #333;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-between;
            min-height: 100vh;
        }
        header {
            background-color: #454141;
            color: #fff;
            padding: 15px 0;
            text-align: center;
            width: 100%;
        }
        form {
            background-color: #fff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input,
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 20px;
            font-size: 16px;
        }
        button {
            padding: 12px 20px;
            border: none;
            background-color: #454141;
            color: #fff;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }
        footer {
            background-color: #454141;
            color: #fff;
            text-align: center;
            padding: 15px 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <header>
        <h1>Add New Book</h1>
    </header>

    <form action="AddBookServlet" method="post">
        <label for="isbn">ISBN</label>
        <input type="text" id="isbn" name="isbn" required>
        
        <label for="title">Title</label>
        <input type="text" id="title" name="title" required>
        
        <label for="author">Author</label>
        <input type="text" id="author" name="author" required>
        
        <label for="publisher">Publisher</label>
        <input type="text" id="publisher" name="publisher" required>
        
        <label for="publicationDate">Publication Date</label>
        <input type="date" id="publicationDate" name="publicationDate" required>
        
        <label for="genre">Genre</label>
        <input type="text" id="genre" name="genre" required>
        
        <label for="language">Language</label>
        <input type="text" id="language" name="language" required>
        
        <label for="price">Price (USD)</label>
        <input type="number" step="0.01" id="price" name="price" required>
        
        <label for="stockQuantity">Stock Quantity</label>
        <input type="number" id="stockQuantity" name="stockQuantity" required>
        
        <label for="description">Description</label>
        <textarea id="description" name="description" rows="4" required></textarea>
        
        <label for="coverImageUrl">Cover Image URL</label>
        <input type="text" id="coverImageUrl" name="coverImageUrl" required>
        
        <button type="submit">Add Book</button>
    </form>
    
    <footer>
        <p>© 2024 Your Book Store</p>
    </footer>
</body>
</html>
