import java.io.File;

public class Tauxdecompress {
	private static File fichier_compressé = new File("fichiercompressee.txt");
	private static File fichier_decompressé = new File("fichierdecompressee.txt");
	
	public Tauxdecompress() {
	}

	public File getCompressed_file() {
		return fichier_compressé;
	}

	public File getDecompressed_file() {
		return fichier_decompressé;
	}
	
	public static float taux_de_compress(File fichier_compressée, File fichier_decompressée) {
		long taille_compression = fichier_compressée.length();
		long taille_decompression = fichier_decompressée.length(); 
		float resultat = (float) taille_decompression / taille_compression;
		
		return 1 - resultat;
	}
	public static void main(String args[]) {
		System.out.println(fichier_decompressé.length());
		
		System.out.println(taux_de_compress(fichier_compressé, fichier_decompressé));
	}
	

}