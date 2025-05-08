from django.shortcuts import render
from .models import Player


def index(request):
    players = Player.objects.all()
    context = {"players_list": players, "app_name": "Players"}
    return render(request, "index.html", context)


def details(request, player_id):
    player = Player.objects.filter(id=player_id).first()
    context = {"player_data": player, "app_name": "Player Details"}
    return render(request, "details.html", context)
