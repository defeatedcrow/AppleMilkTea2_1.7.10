package mods.defeatedcrow.asm;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

/**
 * Original code was created by A.K.
 */
public class AppleMilkCoreModContainer extends DummyModContainer {

	public AppleMilkCoreModContainer() {
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = "AppleMilkCore";
		meta.name = "AppleMilkCore";
		meta.version = "1.1.0";
		meta.authorList = Arrays.asList("defeatedcrow");
		meta.description = "This code is required by AppleMilkTeaMod.";
		meta.url = "http://forum.minecraftuser.jp/viewtopic.php?f=13&t=17657";
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

}
