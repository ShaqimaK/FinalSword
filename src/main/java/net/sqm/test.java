package net.sqm;


import com.google.gson.GsonBuilder;
import org.openjdk.nashorn.internal.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import net.minecraftforge.fml.loading.FMLPaths;

public class test {
    public static void init() throws FileNotFoundException {
        FileReader a = new FileReader(FMLPaths.CONFIGDIR.get().resolve("attributefix.json").toFile());

    }
}
