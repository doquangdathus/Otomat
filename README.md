# Otomat

package: io: thực hiện đọc / ghi file, các đối tượng cần thiết cho chương trình
	Lớp ReadAutomata.java: đọc vào một otomat theo định dạng file testcase1.txt...
	Lớp WriteAutomata.java: ghi một otomat ra file: testcase1_out.txt...
package: main: chứa các lớp xử lý thuật toán:
	Lớp: CKY: thực hiện thuật toán CKY đoán nhận từ thuộc ngôn ngữ
	Lớp: CNF.java: thực hiện chuẩn hoá về dạng chuẩn Chomsky
	Lớp: DFAMinimization: thực hiện tối tiểu hoá otomat đơn định
package: object: chứa các đối tượng cần thiết cho việc xử lý 
	Otomat: Alphabet.java, State.java, Transform.java, TransformFunction.java
	Vẽ đồ thị: MyLink.java, MyNode.java
	Chuẩn hoá văn phạm: Rules.java
Ngoài ra còn một số file test các thuâtj toán đã sử dụng:
	otomat: testcase1->5.txt; các file kết quả ra tương ứng là testcase_out1->5.txt
	Tối tiểu hoá: dfa.txt, dfa2.txt; kết quả 2 file này chạy sẽ in ra màn hình
	Chuẩn hoá văn phạm và thuật toán CKY: cnf1->5.txt; kết quả chạy sẽ in ra màn hình