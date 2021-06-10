//
//  main.swift
//  NSRegularExpression
//
//  Created by 윤재필 on 2021/04/19.
//

import Foundation

//let input = readLine()!.components(separatedBy: [" "]).map { String($0) }
//let (userID, userPW) = (input[0], input[1])

print("아이디를 입력하세요.")
var inp = readLine()!
print ( sginUpRegularExpression(inp, 1) )

print("비밀번호를 입력하세요")
inp = readLine()!
print ( sginUpRegularExpression(inp, 2) )

print("이메일을 입력하세요")
inp = readLine()!
print ( sginUpRegularExpression(inp, 3) )

//영문 소문자-숫자로만 구성된 6자리 이상의 문자열인지 검사
func idRegularExpression(_ input: String) -> Bool{
    let pattern = "^[a-z0-9]{6,}$"
    let regex = try? NSRegularExpression(pattern: pattern)
    if let _ = regex?.firstMatch(in: input, options: [], range: NSRange(location: 0, length: input.count)) {
        print("정규식 통과")
        return true
    }
    print("유효하지 않은 id 형식입니다.")
    return false
}
//영문 대소문자, 숫자, ~!@#$%^&*로만 구성된 8자리 이상의 문자열인지 검사
func pwRegularExpression(input: String) -> Bool{
    let pattern =  "^[A-Za-z0-9~!@#$%^&*]{8,}$"
    let regex = try? NSRegularExpression(pattern: pattern)
    if let _ = regex?.firstMatch(in: input, options: [], range: NSRange(location: 0, length: input.count)) {
        print("정규식 통과")
        return true
    }
    return false
}
//이메일 형식 검사. 세토막으로 구성되어 있음.
func emailRegularExpression(input: String) -> Bool {
    let pattern = "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{3,20}$"
    let regex = try? NSRegularExpression(pattern: pattern)
    if let _ = regex?.firstMatch(in: input, options: [], range: _NSRange(location: 0, length: input.count)){
        print("정규식 통과")
        return true
    }
    return false
}
//위의 정규식 검사를 하나로 통합
//type 1: id, 2: pw, 3: email
func sginUpRegularExpression(_ input: String, _ type: Int) -> Bool{
    var pattern: String = ""
    switch type {
    case 1:
        pattern = "^[A-Za-z0-9]{0,}$"
    case 2:
        //최소 8자, 최소 하나의 영문 대문자/소문자 및 숫자 및 특수문자
        pattern = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*]).{8,20}"
    case 3:
        pattern = "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,20}$"
    default:
        print("[sginUpRegularExpression function] type error")
        return false
    }
    
    let regex = try? NSRegularExpression(pattern: pattern)
    if let _ = regex?.firstMatch(in: input, options: [], range: _NSRange(location: 0, length: input.count)){
        print("정규식 통과")
        return true
    }
    return false
}
