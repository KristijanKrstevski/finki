from django.db import models
from django.contrib import admin

# Create your models here.
class Event(models.Model):
    SPORT_CHOICES = [
        ("Football", "Football"),
        ("Basketball", "Basketball"),
        ("Handball", "Handball")
    ]
    name = models.CharField(max_length=100)
    date_event = models.DateField()
    sport = models.CharField(max_length=20, choices=SPORT_CHOICES)


class Player(models.Model):
    RANK_CHOICE = [
        ("A", "Amateur"),
        ("P", "Professional")
    ]

    name = models.CharField(max_length=100)
    surname = models.CharField(max_length=199)
    year_of_birth = models.IntegerField()
    total_games_played = models.IntegerField()
    total_points = models.DecimalField(max_digits=10 ,decimal_places=2)
    rank = models.CharField(max_length=1, choices=RANK_CHOICE)
    sport = models.CharField(max_length=20, choices=Event.SPORT_CHOICES)


class Team(models.Model):
    name = models.CharField(max_length=100)
    sport = models.CharField(max_length=20, choices=Event.SPORT_CHOICES)
    coach_name = models.CharField(max_length=100)

    def __str__(self):
        return self.name


class Participation(models.Model):
    score = models.IntegerField()
    position = models.IntegerField()

    def __str__(self):
        return f"{self.team.name} in {self.event.name}"


class TeamPlayer(models.Model):
    team = models.ForeignKey(Team, on_delete=models.CASCADE)
    player = models.ForeignKey(Player, on_delete=models.CASCADE)

    def __str__(self):
        return f"{self.team} {self.player}"


class EventParticipation(models.Model):
    event = models.ForeignKey(Event, on_delete=models.CASCADE)
    participation = models.ForeignKey(Participation, on_delete=models.CASCADE)

    def __str__(self):
        return f"{self.event} {self.participation}"


class TeamParticipation(models.Model):
    team = models.ForeignKey(Team, on_delete=models.CASCADE)
    participation = models.ForeignKey(Participation, on_delete=models.CASCADE)

    def __str__(self):
        return f"{self.team} {self.participation}"



admin.site.register(Player)
