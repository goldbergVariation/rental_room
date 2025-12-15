package tool;

import org.mindrot.jbcrypt.BCrypt;
/* bcryptは、パスワードハッシュ化関数の一種で、
 * 同じパスワードでもソルトを組み合わせることで毎回異なるハッシュ値を生成し、
 * レインボーテーブル攻撃などの攻撃を防ぎます。
 * 生成されたハッシュ値には、そのハッシュ値を生成したソルトやストレッチング回数（ハッシュ化の繰り返し回数）の情報も含まれています。 */

public class PasswordUtil {
	// パスワードのハッシュ化
	public static String hash(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}
	
	// 入力されたパスワードとハッシュ化された値との照合
	public static boolean verify(String plainPassword, String hashedPassword) {
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}
}
