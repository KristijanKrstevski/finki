from django.contrib import admin
from .models import Book, BookRating, GenreBook, Genre, TranslatorBook, Translator

# Register your models here.
admin.site.register(Book)
admin.site.register(Genre)
admin.site.register(Translator)
admin.site.register(GenreBook)
admin.site.register(TranslatorBook)
admin.site.register(BookRating)
