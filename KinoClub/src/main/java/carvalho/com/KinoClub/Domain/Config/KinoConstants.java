package carvalho.com.KinoClub.Domain.Config;

import java.util.ArrayList;
import java.util.Arrays;
//Should be migrated to some file
public final class KinoConstants {
	public static final ArrayList<String> CorsDomains = (ArrayList<String>) Arrays.asList("http://localhost:3000","https://joao-r-carvalho.github.io");
	public static final String AuthenticationCookieName = "KCAuthentication";
	public static final String AuthenticatedUser = "AuthenticatedUser";


}
