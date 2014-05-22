public class Trig {

	public static void triggerTrig(int i) {

		if (RaceCollection.CurrentRace.num_racers() == 0)
			specialCaseRace.weJustHadAnonymousRaceRunning = true;

		if (!specialCaseRace.weJustHadAnonymousRaceRunning) {
			if (RaceCollection.CurrentRace.isThereRacerThatHasNotBeenStarted()
					&& ((Channel.getState(i) && !RaceCollection.CurrentRace
							.isStarted()) || (Channel.getState(i) && !RaceCollection.CurrentRace
							.isFinished())))
				Start.triggerStart();

			else if (Channel.getState(i)
					&& !RaceCollection.CurrentRace
							.isThereRacerThatHasNotBeenStarted())
				Finish.triggerFin();
			else
				System.out.println("Trig Error: " + i);

		} else
			specialCaseRace.triggerSpecialCase(i);
	}
}
