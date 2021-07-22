姓名：龐懋翔
學號：407235049
e…mail:yeiogy123@yahoo.com.tw
完成項目：
C++,JAVA.
撰寫一個程式讀取polydata.txt的內容直到檔案結束；每一次讀取都需要詢問使用者輸入的x為多少，並輸出該polynomial的長相、一次微分的長相、polynomial(x)的結果、一次微分的結果。每一項都需標明清楚(10%)
　txt的內容為: 若干行的數列，數列中每一個數字都由空白隔開。每一行的第一個數字為最高次方項的次方數n，接下來n+1個數字表示每一項的係數，以昇冪方式排列
2. 繳交作業時一併繳交同學寫程式時自己測試用的測資(10%)

3.該程式需要包含以下四項副函式(70%):
　I. highestPower(float coff[]): 傳入一個包含所有係數的float型態陣列進去；return最高次方項的次方數--10%
　II. display(int highestPow, float coff[]): 傳入一個整數為最高次方項的次方數、包含所有係數的float型態陣列；print出該polynomial的長相，每一項都需用 '+' 隔開，以昇冪方式排列--20%
　　例如: highestPow = 3, coff[] = {1, 2, 3, 4}，則必須print出1 + 2*x + 3*x*x + 4*x*x*x
　III. derivative(int highestPow, float coff[], float dev_coff[]): 傳入一個整數為最高次方項的次方數、包含所有係數的float型態陣列、包含所有一次微分係數的float型態陣列；經由highestPow和coff推算出dev_coff的內容。因為dev_coff是直接傳空白的陣列，所以不需要return任何東西--20%
　IV. compute(int highestPow, float coff[], float x): 傳入一個最高次方項的次方數、包含所有係數的float型態陣列、一個x；return polynomial(x)的結果--20%
BONUS:
完成DEBUG MODE 

