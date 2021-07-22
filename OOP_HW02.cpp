#include <iostream>
#include <fstream>
#include<sstream>
#include<vector>
#include <iomanip>
#include <cmath>
#include<cctype>
#include<cstring>
#define KEYBOARD_DEBUG 1
#define FILE_DEBUG 0
using namespace std;
using std::vector;
int judgeCoe(char []);
int judgePow(char []);
int highestPower(float[]);
void display(int , float []);
void derivative(int , float[],float[]);
float compute(int , float [], float );
void search_file(ifstream&infile) ;
void Key_board();
int main()
{

    ifstream FileInput;
    string FileNameIn = "polydata.txt";
    FileInput.open(FileNameIn,ios::in);
    int num ;
    int mode;
    cout <<"please input 1 which the data scan by keyboard or 0 which"
           "scan the data from the file to run the program : "<<endl;
    cin >> num;
    if(num)
        mode = KEYBOARD_DEBUG;
    else
        mode = FILE_DEBUG;
    if(mode)
    {
        Key_board();
    }
    else
    {
        if (!FileInput) {
            cout << "檔案: " << FileNameIn << " 開啟失敗!" << endl;
            exit(1);
        } else {
            search_file(FileInput);
            cout << "the program is completed. " << endl;
        }
    }
    return 0;
}
void Key_board()
{
    while(1)
    {

        char inputX[20];
        cout << "please input x variable of the function or type [exit] to shut down " << endl;
        cin >> inputX;
        if(!judgePow(inputX) && strcmp(inputX,"exit")!=0)
        {
            cout<<"your x input is wrong,please input the number";
            continue;
        }
        else if (strcmp(inputX,"exit")==0)
        {
            cout<<"program shut down."<<endl;
            exit(3);

        }
        else
            cout << "your x equals to " << inputX << "." << endl;
        float target = atof(inputX);
        cout << "please input the highest power in the function :" << endl;
        float coefficient, *poly = new float[6](), *dev_coff = new float[5]();
        char tmp[20];
        cin >> tmp;
        int count = 0;
        bool status = true;
        if (!judgePow(tmp))
        {
            cout << "The highest number must be integer" << endl;
            status = false;
            continue;
        }
        else
            poly[0] = atoi(tmp);
        cout <<"please input the each of the coefficient in the function"<<endl;
        int currentIndex = 1 ;
        while(count <= poly[0])
        {
            char input[20];
            cin >> input;
            if (!judgeCoe(input))
            {
                status = false;
                cout << "Each of the data in the file must be number." << endl;
                cout << "your input [" << currentIndex << "] is [" << input << "]." << endl;
                cout << "so please input the correct number in the file." << endl;
                break;
            }
            coefficient = atof(input);
            poly[currentIndex] = coefficient;
            currentIndex++;
            count++;
        }
        if (!status)
            continue;
        if (currentIndex-1  != highestPower(poly)+1 )
        {
            cout << "The input of the coefficient are not sufficient" << endl;
            exit(2);
        }
        else
        {
            cout << "f(x) = ";
            display(highestPower(poly), poly);
            dev_coff[0] = highestPower(poly) - 1;
            derivative(highestPower(poly), poly, dev_coff);
            cout << "f'(x) = ";
            display(highestPower(dev_coff), dev_coff);
            cout << "f(" << target << ") = " << fixed << setprecision(3)
                 << compute(highestPower(poly), poly, target) << endl;
            cout << "f'(" << target << ") = " << fixed << setprecision(3)
                 << compute(highestPower(dev_coff), dev_coff, target) << endl;
        }
    }
}
void search_file(std:: ifstream&FileInput)
{
    string buffer;
    while (getline(FileInput, buffer)) {
        float target;
        cout << "please input x variable of the function " << endl;
        cin >> target;
        cout << "your x equals to " << target << "." << endl;
        char temp[20];
        bool power = false;
        bool status = true;

        int currentIndex = 0, tmp;
        float coefficient, *poly = new float[6](), *dev_coff = new float[5]();
        stringstream ss(buffer);
        while (ss >> temp) {
            if (!power) {
                if (!judgePow(temp)) {
                    cout << "The highest number must be integer" << endl;
                    status = false;
                    break;
                } else {
                    power = true;
                    tmp = atoi(temp);
                    poly[currentIndex++] = (float) tmp;
                }
            } else {
                if (!judgeCoe(temp)) {
                    status = false;
                    cout << "Each of the data in the file must be number." << endl;
                    cout << "your input [" << currentIndex << "] is [" << temp << "]." << endl;
                    cout << "so please input the correct number in the file." << endl;
                    break;
                }
                coefficient = atof(temp);
                poly[currentIndex++] = coefficient;
            }
        }
        if (!status)
            continue;
        if (currentIndex - 1 != highestPower(poly) + 1) {
            cout << "The input of the coefficient are not sufficient" << endl;
            exit(2);
        } else {
            cout << "f(x) = ";
            display(highestPower(poly), poly);
            dev_coff[0] = highestPower(poly) - 1;
            derivative(highestPower(poly), poly, dev_coff);
            cout << "f'(x) = ";
            display(highestPower(dev_coff), dev_coff);
            cout << "f(" << target << ") = " << fixed << setprecision(3)
                 << compute(highestPower(poly), poly, target) << endl;
            cout << "f'(" << target << ") = " << fixed << setprecision(3)
                 << compute(highestPower(dev_coff), dev_coff, target) << endl;
        }
    }
}
int judgeCoe(char input[])
{
    for(int i = 0 ; i < 20 ; i++)
        if(input[i] != '\0' && input[i] != '.' && !isdigit(input[i])
        && input[i] != 'E' && input[i] != 'e' && input[i] != '-')
            return 0;
        else if(input[i]=='\0')
            return 1;
    return 1;

}
int judgePow(char input[])
{
    for(int i = 0 ; i < 20;i++)
        if(input[i]!='\0'&&!isdigit(input[i]))
            return 0;
        else if(input[i] == '\0')
            return 1;
    return 1;
}
int  highestPower(float coff[])
{
    return coff[0];
}
void display(int highestPow, float coff[])
{
    for(int current = 1 ; current <= highestPow+1; current++)
    {
        if(coff[current]!=0)
        {
            cout<<coff[current];
            for (int x = 1; x < current; x++)
            {
                cout << "x";
                if (x + 1 != current)
                    cout << "*";
            }
            if (current <= highestPow && coff[current+1]!=0)
                cout << " + ";
        }
    }
    cout << endl;
}
void derivative(int highestPow, float coff[], float dev_coff[])
{

    int dev_highestPow = highestPow;
    for(int i = highestPow ; i >= 1 ; i--)
    {
        dev_coff[dev_highestPow] = int(i) * coff[i+1];
        dev_highestPow--;
    }

}
float compute(int highestPow, float coff[], float x)
{
    float total = coff[1];
    for(int i = 2 ; i <= highestPow+1 ; i++)
    {
        float xTotal = 0.0;
        xTotal = pow(x,i-1);
        total = coff[i]*xTotal + total ;
    }
    return total ;
}
