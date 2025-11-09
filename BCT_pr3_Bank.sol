
// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.0;

contract Bank{
    // dtype acessSpecifier varName (= value);
    // address public accHolder;
    // uint balance = 0;

    mapping(address => uint) balanceOf;

    // constructor(){
    //     accHolder = msg.sender;
    // }

    // function functionName(param) accessSpecifier returns(returnType) {
    //     body;
    // }

    // function createAccount() public payable {
    //     require(msg.value == 1 ether, "Not enough fee to create an account.");
    //     balanceOf[msg.sender] = -1;
    // }

    function deposit() public payable {
        require(msg.value > 0, "Deposit should be greater than zero.");
        balanceOf[msg.sender] += msg.value;
    }

    function withdraw() public {
        require(balanceOf[msg.sender] > 0, "Your balance should be greater than zero.");
        payable(msg.sender).transfer(balanceOf[msg.sender]);
        balanceOf[msg.sender] = 0;
    }

    function showBalance() public view returns(uint) {
        return balanceOf[msg.sender];
    }

}
