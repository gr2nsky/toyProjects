//
//  ViewController.swift
//  FirstSroryboard
//
//  Created by 윤재필 on 2021/04/14.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var lblTest: UILabel!
    var num = 0

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func btnTest(_ sender: UIButton) {
        num += 1
        lblTest.text = String(num)
    }
    
}

