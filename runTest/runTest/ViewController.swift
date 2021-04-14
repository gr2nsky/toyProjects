//
//  ViewController.swift
//  runTest
//
//  Created by 윤재필 on 2021/04/13.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var imgTitle: UIImageView!
    @IBOutlet weak var tfInputID: UITextField!
    @IBOutlet weak var tfInputPW: UITextField!
    @IBOutlet weak var btnLogin: UIButton!
    @IBOutlet weak var lblFindAccount: UILabel!
    @IBOutlet weak var lblSignUp: UILabel!
    @IBOutlet weak var btnLoginApple: UIButton!
    @IBOutlet weak var btnLoginGoogle: UIButton!
    @IBOutlet weak var btnLoginNaver: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    @IBAction func loginTouch(_ sender: UIButton) {
        let alert: UIAlertController =
            UIAlertController(title: "로그인 결과", message: "로그인 성공", preferredStyle: .alert)
        let alertBtn: UIAlertAction =
            UIAlertAction(title: "확인", style: .default, handler: nil)
        alert.addAction(alertBtn)
        self.present(alert, animated: true, completion: nil)
    }
}

