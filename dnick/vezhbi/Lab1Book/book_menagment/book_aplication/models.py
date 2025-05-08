from django.db import models
from django.contrib.auth.models import User


# Create your models here.

class Genre(models.Model):
    name = models.CharField(max_length=100)
    description = models.TextField()

    def __str__(self):
        return self.name


class Translator(models.Model):
    name = models.CharField(max_length=100)
    nationality = models.CharField(max_length=100)
    birth_date = models.DateField()

    def __str__(self):
        return self.name


class Book(models.Model):
    title = models.CharField(max_length=100)
    cover = models.ImageField(upload_to='Covers/', null=True, blank=True)
    author = models.CharField(max_length=100)
    page_number = models.IntegerField()
    readable = models.BooleanField()
    publish_date = models.DateField()
    owner = models.ForeignKey(User, on_delete=models.CASCADE)

    def __str__(self):
        return f"{self.title}"


class BookRating(models.Model):
    book = models.ForeignKey(Book, on_delete=models.CASCADE)
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    rating = models.IntegerField()
    comment = models.TextField(null=True, blank=True)

    def __str__(self):
        return f" {self.rating}!"


class TranslatorBook(models.Model):
    translator = models.ForeignKey(Translator, on_delete=models.CASCADE)
    book = models.ForeignKey(Book, on_delete=models.CASCADE)

    def str_(self):
        return f"{self.book.title} translated by {self.translator.name}!"


class GenreBook(models.Model):
    genre = models.ForeignKey(Genre, on_delete=models.CASCADE)
    book = models.ForeignKey(Book, on_delete=models.CASCADE)

    def __str__(self):
        return f"{self.book.title} - {self.genre.name}"
