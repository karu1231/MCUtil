package karu1231.testpl.scheduler;

import karu1231.testpl.privateGit.TestPL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Schedulers {

	private static final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	private static final Plugin plugin = TestPL.plugin;

	/* タスクが作られたすぐ次のtickで起動。 同期に使う。 */
	public static void runTask() {

		scheduler.runTask(plugin, new Runnable() {

			@Override
			public void run() {

				/* 実行するコード */

			}

		});

	}

	/* タスクが作られてから一定時間置かれたtickで起動。 */
	public static void delayTask() {

		/* 遅らせる時間を引数としてもたせる。 設定数値の単位はtickで1秒 = 20tick */
		long delaytick = 20L;

		scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {

				/* 実行するコード */

			}

		}, delaytick);

	}

	/* タスクが作られてから止められるまで一生設定した時間ごとに実行される。 */
	public static void repeatTask() {

		/* リピートごとの遅延時間とタスクが作られてから一番最初のリピートの遅延時間をを引数としてもたせる。
		 * 最初のリピートの遅延時間を0にすると、タスクが作られた次のtickで実行される。
		 * 設定数値の単位はtickで1秒 = 20tick */
		long firstdelay = 0L;
		long repeattick = 10L;

		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {

				/* 実行されたコード */

			}

		}, firstdelay, repeattick);

	}
}
