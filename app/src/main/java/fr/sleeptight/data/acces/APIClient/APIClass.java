package fr.sleeptight.data.acces.APIClient;

import java.util.List;

/**
 * Created by User on 6/13/2016.
 */
public class APIClass {
    public static class ProfileResetting
    {
        public String username;
        public String pw_hash;
    }

    public static class SimpleUsr
    {
        public String username;
        public String password;

        public SimpleUsr(String username, String password)
        {
            this.username = username;
            this.password = password;
        }
    }

    public static class SleepData
    {
        public int awakeCount;
        public int awakeningsCount;
        public int awakeDuration;
        public String dateOfSleep;
        public int duration;
        public int efficiency;
        public boolean isMainSleep;
        public int minutesAfterWakeup;
        public int minutesAwake;
        public int minutesAsleep;
        public int minutesToFallAsleep;
        public int restlessCount;
        public int restlessDuration;
        public String startTime;
        public int timeInBed;
        public List<String> dateTimeStateAwake;
        public List<String> dateTimeStateReallyAwake;

        public SleepData(String dateOfSleep, int awakeCount, int awakeningsCount,int awakeDuration, int duration, int efficiency,
                         boolean isMainSleep, int minutesAfterWakeup, int minutesAsleep, int minutesToFallAsleep, int restlessCount, int minutesAwake,
                         int restlessDuration, String startTime, int timeInBed, List<String> dateTimeStateAwake, List<String> dateTimeStateReallyAwake)
        {
            this.dateOfSleep = dateOfSleep;
            this.awakeCount = awakeCount;
            this.awakeningsCount = awakeningsCount;
            this.awakeDuration = awakeDuration;
            this.duration = duration;
            this.efficiency = efficiency;
            this.isMainSleep = isMainSleep;
            this.minutesAfterWakeup = minutesAfterWakeup;
            this.minutesAsleep = minutesAsleep;
            this.minutesToFallAsleep = minutesToFallAsleep;
            this.restlessCount = restlessCount;
            this.restlessDuration =restlessDuration;
            this.startTime = startTime;
            this.timeInBed = timeInBed;
            this.dateTimeStateAwake = dateTimeStateAwake;
            this.minutesAwake = minutesAwake;
            this.dateTimeStateReallyAwake = dateTimeStateReallyAwake;
        }
    }

    public static class ReponseProfile
    {
        public String username;
        public String returns;
    }

    public static class Profile
    {
        public boolean gender;
        public int age;
        public boolean isNoon;

        public Profile(boolean gender, int age, boolean isNoon)
        {
            this.gender = gender;
            this.age = age;
            this.isNoon = isNoon;
        }
    }

    public static class ProfileWithUser
    {
        public boolean gender;
        public int age;
        public boolean isNoon;
        public String username;
    }
}
