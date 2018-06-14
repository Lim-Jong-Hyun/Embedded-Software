pragma solidity ^0.4.0;

contract BlockTel {
    
    string checkin = "checkin";
    string checkout = "checkout";
    uint8 cnt;
    
    mapping (uint8 => string) public display;
    
    function checkIn(string str) public {
        checkin = str;
    }
    
    function checkOut(string str) public {
        checkout = str;
    }
    
    function getIn() public constant returns(string) {
        return checkin;
    }
    function getOut() public constant returns(string){
        return checkout;
    }
    function DisplaySet(string dsp) public {
        display[cnt] = dsp;
        cnt++;
    }
    function DisplayGet(uint8 number) public constant returns (string) {
        return display[number];
    }
    function count() public constant returns(uint8){
        return cnt;
    }
}