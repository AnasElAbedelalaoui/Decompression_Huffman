import java.io.File;

public class Tauxdecompress {
	private static File fichier_compress� = new File("fichiercompressee.txt");
	private static File fichier_decompress� = new File("fichierdecompressee.txt");
	
	public Tauxdecompress() {
	}

	public File getCompressed_file() {
		return fichier_compress�;
	}

	public File getDecompressed_file() {
		return fichier_decompress�;
	}
	
	public static float taux_de_compress(File fichier_compress�e, File fichier_decompress�e) {
		long taille_compression = fichier_compress�e.length();
		long taille_decompression = fichier_decompress�e.length(); 
		float resultat = (float) taille_decompression / taille_compression;
		
		return 1 - resultat;
	}
	public static void main(String args[]) {
		System.out.println(fichier_decompress�.length());
		
		System.out.println(taux_de_compress(fichier_compress�, fichier_decompress�));
	}
	

}