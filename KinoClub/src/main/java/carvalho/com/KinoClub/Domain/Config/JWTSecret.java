package carvalho.com.KinoClub.Domain.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JWTSecret {
	private static String JWT_SECRET = "sdlfgnsldfgnIBOY384T38FDNSb3VIUYVvITYVI369FSOIDPFABLBEWTERTGWERGUOAS3647352964Wogysdifvidsfbosdhf684395BDDGbohvivIdf7DTF7dtfg8oSBDFOSDF0Y39BSD";

	public static synchronized String GetInstance() {
		if (JWT_SECRET == null) {
			try {
				Path path = Paths.get("src/main/resources/secret.txt");
				JWT_SECRET = Files.readAllLines(path).get(0);
			} catch (IOException e) {
				e.printStackTrace();
				return null;

			}
			;

		}
		return JWT_SECRET;
	}

}
