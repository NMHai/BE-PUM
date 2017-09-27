0x00401262:	pushl $0x18<UINT8>
0x00401264:	pushl $0x408190<UINT32>
0x00401269:	call 0x00401e80
0x00401e80:	pushl $0x401ed4<UINT32>
0x00401e85:	movl %eax, %fs:0
0x00401e8b:	pushl %eax
0x00401e8c:	movl %eax, 0x10(%esp)
0x00401e90:	movl 0x10(%esp), %ebp
0x00401e94:	leal %ebp, 0x10(%esp)
0x00401e98:	subl %esp, %eax
0x00401e9a:	pushl %ebx
0x00401e9b:	pushl %esi
0x00401e9c:	pushl %edi
0x00401e9d:	movl %eax, -8(%ebp)
0x00401ea0:	movl -24(%ebp), %esp
0x00401ea3:	pushl %eax
0x00401ea4:	movl %eax, -4(%ebp)
0x00401ea7:	movl -4(%ebp), $0xffffffff<UINT32>
0x00401eae:	movl -8(%ebp), %eax
0x00401eb1:	leal %eax, -16(%ebp)
0x00401eb4:	movl %fs:0, %eax
0x00401eba:	ret

0x0040126e:	movl %edi, $0x94<UINT32>
0x00401273:	movl %eax, %edi
0x00401275:	call 0x00403080
0x00403080:	cmpl %eax, $0x1000<UINT32>
0x00403085:	jae 14
0x00403087:	negl %eax
0x00403089:	addl %eax, %esp
0x0040308b:	addl %eax, $0x4<UINT8>
0x0040308e:	testl (%eax), %eax
0x00403090:	xchgl %esp, %eax
0x00403091:	movl %eax, (%eax)
0x00403093:	pushl %eax
0x00403094:	ret

0x0040127a:	movl -24(%ebp), %esp
0x0040127d:	movl %esi, %esp
0x0040127f:	movl (%esi), %edi
0x00401281:	pushl %esi
0x00401282:	call GetVersionExA@kernel32.dll
GetVersionExA@kernel32.dll: API Node	
0x00401288:	movl %ecx, 0x10(%esi)
0x0040128b:	movl 0x40abec, %ecx
0x00401291:	movl %eax, 0x4(%esi)
0x00401294:	movl 0x40abf8, %eax
0x00401299:	movl %edx, 0x8(%esi)
0x0040129c:	movl 0x40abfc, %edx
0x004012a2:	movl %esi, 0xc(%esi)
0x004012a5:	andl %esi, $0x7fff<UINT32>
0x004012ab:	movl 0x40abf0, %esi
0x004012b1:	cmpl %ecx, $0x2<UINT8>
0x004012b4:	je 0x004012c2
0x004012c2:	shll %eax, $0x8<UINT8>
0x004012c5:	addl %eax, %edx
0x004012c7:	movl 0x40abf4, %eax
0x004012cc:	xorl %edi, %edi
0x004012ce:	pushl %edi
0x004012cf:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32.dll: API Node	
0x004012d5:	cmpw (%eax), $0x5a4d<UINT16>
0x004012da:	jne 31
0x004012dc:	movl %ecx, 0x3c(%eax)
0x004012df:	addl %ecx, %eax
0x004012e1:	cmpl (%ecx), $0x4550<UINT32>
0x004012e7:	jne 18
0x004012e9:	movzwl %eax, 0x18(%ecx)
0x004012ed:	cmpl %eax, $0x10b<UINT32>
0x004012f2:	je 0x00401313
0x00401313:	cmpl 0x74(%ecx), $0xe<UINT8>
0x00401317:	jbe -30
0x00401319:	xorl %eax, %eax
0x0040131b:	cmpl 0xe8(%ecx), %edi
0x00401321:	setne %al
0x00401324:	movl -28(%ebp), %eax
0x00401327:	pushl $0x1<UINT8>
0x00401329:	call 0x00403024
0x00403024:	xorl %eax, %eax
0x00403026:	cmpl 0x4(%esp), %eax
0x0040302a:	pushl $0x0<UINT8>
0x0040302c:	sete %al
0x0040302f:	pushl $0x1000<UINT32>
0x00403034:	pushl %eax
0x00403035:	call HeapCreate@kernel32.dll
HeapCreate@kernel32.dll: API Node	
0x0040303b:	testl %eax, %eax
0x0040303d:	movl 0x40b2c0, %eax
0x00403042:	je 42
0x00403044:	call 0x0040300a
0x0040300a:	cmpl 0x40abec, $0x2<UINT8>
0x00403011:	jne 13
0x00403013:	cmpl 0x40abf8, $0x5<UINT8>
0x0040301a:	jb 4
0x0040301c:	xorl %eax, %eax
0x0040301e:	incl %eax
0x0040301f:	ret

0x00403049:	cmpl %eax, $0x3<UINT8>
0x0040304c:	movl 0x40b2c4, %eax
0x00403051:	jne 0x00403071
0x00403071:	xorl %eax, %eax
0x00403073:	incl %eax
0x00403074:	ret

0x0040132e:	popl %ecx
0x0040132f:	testl %eax, %eax
0x00401331:	jne 0x0040133b
0x0040133b:	call 0x004021f5
0x004021f5:	call 0x004032c1
0x004032c1:	pushl %esi
0x004032c2:	pushl %edi
0x004032c3:	xorl %esi, %esi
0x004032c5:	movl %edi, $0x40ad48<UINT32>
0x004032ca:	cmpl 0x40a594(,%esi,8), $0x1<UINT8>
0x004032d2:	jne 0x004032f2
0x004032d4:	leal %eax, 0x40a590(,%esi,8)
0x004032db:	movl (%eax), %edi
0x004032dd:	pushl $0xfa0<UINT32>
0x004032e2:	pushl (%eax)
0x004032e4:	addl %edi, $0x18<UINT8>
0x004032e7:	call 0x0040552d
0x0040552d:	pushl $0x10<UINT8>
0x0040552f:	pushl $0x408e88<UINT32>
0x00405534:	call 0x00401e80
0x00405539:	movl %eax, 0x40af10
0x0040553e:	testl %eax, %eax
0x00405540:	jne 0x00405579
0x00405542:	cmpl 0x40abec, $0x1<UINT8>
0x00405549:	je 36
0x0040554b:	pushl $0x40825c<UINT32>
0x00405550:	call GetModuleHandleA@kernel32.dll
0x00405556:	testl %eax, %eax
0x00405558:	je 21
0x0040555a:	pushl $0x408e5c<UINT32>
0x0040555f:	pushl %eax
0x00405560:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x00405566:	movl 0x40af10, %eax
0x0040556b:	testl %eax, %eax
0x0040556d:	jne 0x00405579
0x00405579:	andl -4(%ebp), $0x0<UINT8>
0x0040557d:	pushl 0xc(%ebp)
0x00405580:	pushl 0x8(%ebp)
0x00405583:	call InitializeCriticalSectionAndSpinCount@kernel32.dll
InitializeCriticalSectionAndSpinCount@kernel32.dll: API Node	
0x00405585:	movl -32(%ebp), %eax
0x00405588:	jmp 0x004055ae
0x004055ae:	orl -4(%ebp), $0xffffffff<UINT8>
0x004055b2:	call 0x00401ebb
0x00401ebb:	movl %ecx, -16(%ebp)
0x00401ebe:	movl %fs:0, %ecx
0x00401ec5:	popl %ecx
0x00401ec6:	popl %edi
0x00401ec7:	popl %esi
0x00401ec8:	popl %ebx
0x00401ec9:	leave
0x00401eca:	pushl %ecx
0x00401ecb:	ret

0x004055b7:	ret

0x004032ec:	testl %eax, %eax
0x004032ee:	popl %ecx
0x004032ef:	popl %ecx
0x004032f0:	je 12
0x004032f2:	incl %esi
0x004032f3:	cmpl %esi, $0x24<UINT8>
0x004032f6:	jl 0x004032ca
0x004032f8:	xorl %eax, %eax
0x004032fa:	incl %eax
0x004032fb:	popl %edi
0x004032fc:	popl %esi
0x004032fd:	ret

0x004021fa:	testl %eax, %eax
0x004021fc:	jne 0x00402206
0x00402206:	pushl %esi
0x00402207:	pushl %edi
0x00402208:	pushl $0x40825c<UINT32>
0x0040220d:	call GetModuleHandleA@kernel32.dll
0x00402213:	movl %edi, %eax
0x00402215:	testl %edi, %edi
0x00402217:	je 107
0x00402219:	movl %esi, 0x408044
0x0040221f:	pushl $0x408250<UINT32>
0x00402224:	pushl %edi
0x00402225:	call GetProcAddress@kernel32.dll
0x00402227:	pushl $0x408244<UINT32>
0x0040222c:	pushl %edi
0x0040222d:	movl 0x40abd8, %eax
0x00402232:	call GetProcAddress@kernel32.dll
0x00402234:	pushl $0x408238<UINT32>
0x00402239:	pushl %edi
0x0040223a:	movl 0x40abdc, %eax
0x0040223f:	call GetProcAddress@kernel32.dll
0x00402241:	pushl $0x408230<UINT32>
0x00402246:	pushl %edi
0x00402247:	movl 0x40abe0, %eax
0x0040224c:	call GetProcAddress@kernel32.dll
0x0040224e:	cmpl 0x40abdc, $0x0<UINT8>
0x00402255:	movl 0x40abe4, %eax
0x0040225a:	jne 0x00402284
0x00402284:	pushl $0x40207f<UINT32>
0x00402289:	call FlsAlloc@kernel32.dll
FlsAlloc@kernel32.dll: API Node	
0x0040228f:	cmpl %eax, $0xffffffff<UINT8>
0x00402292:	movl 0x40a2d0, %eax
0x00402297:	je 65
0x00402299:	xorl %edi, %edi
0x0040229b:	pushl $0x8c<UINT32>
0x004022a0:	incl %edi
0x004022a1:	pushl %edi
0x004022a2:	call 0x00402705
0x00402705:	pushl $0x10<UINT8>
0x00402707:	pushl $0x4082a8<UINT32>
0x0040270c:	call 0x00401e80
0x00402711:	movl %ecx, 0x8(%ebp)
0x00402714:	testl %ecx, %ecx
0x00402716:	jbe 19
0x00402718:	pushl $0xffffffe0<UINT8>
0x0040271a:	popl %eax
0x0040271b:	xorl %edx, %edx
0x0040271d:	divl %eax, %ecx
0x0040271f:	cmpl %eax, 0xc(%ebp)
0x00402722:	jae 0x0040272b
0x0040272b:	imull %ecx, 0xc(%ebp)
0x0040272f:	movl %esi, %ecx
0x00402731:	movl -32(%ebp), %esi
0x00402734:	testl %esi, %esi
0x00402736:	jne 0x00402739
0x00402739:	xorl %edi, %edi
0x0040273b:	movl -28(%ebp), %edi
0x0040273e:	cmpl %esi, $0xffffffe0<UINT8>
0x00402741:	ja 101
0x00402743:	cmpl 0x40b2c4, $0x3<UINT8>
0x0040274a:	jne 0x00402793
0x00402793:	testl %edi, %edi
0x00402795:	jne 61
0x00402797:	pushl %esi
0x00402798:	pushl $0x8<UINT8>
0x0040279a:	pushl 0x40b2c0
0x004027a0:	call HeapAlloc@kernel32.dll
HeapAlloc@kernel32.dll: API Node	
0x004027a6:	movl %edi, %eax
0x004027a8:	testl %edi, %edi
0x004027aa:	jne 0x004027d4
0x004027d4:	movl %eax, %edi
0x004027d6:	call 0x00401ebb
0x004027db:	ret

0x004022a7:	movl %esi, %eax
0x004022a9:	testl %esi, %esi
0x004022ab:	popl %ecx
0x004022ac:	popl %ecx
0x004022ad:	je 43
0x004022af:	pushl %esi
0x004022b0:	pushl 0x40a2d0
0x004022b6:	call FlsSetValue@kernel32.dll
FlsSetValue@kernel32.dll: API Node	
0x004022bc:	testl %eax, %eax
0x004022be:	je 26
0x004022c0:	movl 0x54(%esi), $0x40a2d8<UINT32>
0x004022c7:	movl 0x14(%esi), %edi
0x004022ca:	call GetCurrentThreadId@kernel32.dll
GetCurrentThreadId@kernel32.dll: API Node	
0x004022d0:	orl 0x4(%esi), $0xffffffff<UINT8>
0x004022d4:	movl (%esi), %eax
0x004022d6:	movl %eax, %edi
0x004022d8:	jmp 0x004022e1
0x004022e1:	popl %edi
0x004022e2:	popl %esi
0x004022e3:	ret

0x00401340:	testl %eax, %eax
0x00401342:	jne 0x0040134c
0x0040134c:	call 0x00402f82
0x00402f82:	pushl $0xc<UINT8>
0x00402f84:	pushl $0x4086c0<UINT32>
0x00402f89:	call 0x00401e80
0x00402f8e:	movl -28(%ebp), $0x40934c<UINT32>
0x00402f95:	cmpl -28(%ebp), $0x40934c<UINT32>
0x00402f9c:	jae 0x00402fc0
0x00402fc0:	call 0x00401ebb
0x00402fc5:	ret

0x00401351:	movl -4(%ebp), %edi
0x00401354:	call 0x00402d84
0x00402d84:	subl %esp, $0x48<UINT8>
0x00402d87:	pushl %ebx
0x00402d88:	movl %ebx, $0x480<UINT32>
0x00402d8d:	pushl %ebx
0x00402d8e:	call 0x004034ec
0x004034ec:	pushl 0x40aef8
0x004034f2:	pushl 0x8(%esp)
0x004034f6:	call 0x004034c0
0x004034c0:	cmpl 0x4(%esp), $0xffffffe0<UINT8>
0x004034c5:	ja 34
0x004034c7:	pushl 0x4(%esp)
0x004034cb:	call 0x00403445
0x00403445:	pushl $0xc<UINT8>
0x00403447:	pushl $0x408718<UINT32>
0x0040344c:	call 0x00401e80
0x00403451:	movl %esi, 0x8(%ebp)
0x00403454:	cmpl 0x40b2c4, $0x3<UINT8>
0x0040345b:	jne 0x0040348b
0x0040348b:	testl %esi, %esi
0x0040348d:	jne 0x00403490
0x00403490:	cmpl 0x40b2c4, $0x1<UINT8>
0x00403497:	je 0x0040349f
0x0040349f:	pushl %esi
0x004034a0:	pushl $0x0<UINT8>
0x004034a2:	pushl 0x40b2c0
0x004034a8:	call HeapAlloc@kernel32.dll
0x004034ae:	call 0x00401ebb
0x004034b3:	ret

0x004034d0:	testl %eax, %eax
0x004034d2:	popl %ecx
0x004034d3:	jne 0x004034eb
0x004034eb:	ret

0x004034fb:	popl %ecx
0x004034fc:	popl %ecx
0x004034fd:	ret

0x00402d93:	testl %eax, %eax
0x00402d95:	popl %ecx
0x00402d96:	jne 0x00402da0
0x00402da0:	movl 0x40b2e0, %eax
0x00402da5:	movl 0x40b2c8, $0x20<UINT32>
0x00402daf:	leal %ecx, 0x480(%eax)
0x00402db5:	jmp 0x00402dd5
0x00402dd5:	cmpl %eax, %ecx
0x00402dd7:	jb 0x00402db7
0x00402db7:	orl (%eax), $0xffffffff<UINT8>
0x00402dba:	andl 0x8(%eax), $0x0<UINT8>
0x00402dbe:	movb 0x4(%eax), $0x0<UINT8>
0x00402dc2:	movb 0x5(%eax), $0xa<UINT8>
0x00402dc6:	movl %ecx, 0x40b2e0
0x00402dcc:	addl %eax, $0x24<UINT8>
0x00402dcf:	addl %ecx, $0x480<UINT32>
0x00402dd9:	pushl %ebp
0x00402dda:	pushl %esi
0x00402ddb:	pushl %edi
0x00402ddc:	leal %eax, 0x14(%esp)
0x00402de0:	pushl %eax
0x00402de1:	call GetStartupInfoA@kernel32.dll
GetStartupInfoA@kernel32.dll: API Node	
0x00402de7:	cmpw 0x46(%esp), $0x0<UINT8>
0x00402ded:	je 0x00402edc
0x00402edc:	xorl %ebx, %ebx
0x00402ede:	movl %ecx, 0x40b2e0
0x00402ee4:	leal %eax, (%ebx,%ebx,8)
0x00402ee7:	leal %esi, (%ecx,%eax,4)
0x00402eea:	cmpl (%esi), $0xffffffff<UINT8>
0x00402eed:	jne 111
0x00402eef:	testl %ebx, %ebx
0x00402ef1:	movb 0x4(%esi), $0xffffff81<UINT8>
0x00402ef5:	jne 0x00402f01
0x00402ef7:	pushl $0xfffffff6<UINT8>
0x00402ef9:	popl %eax
0x00402efa:	jmp 0x00402f0b
0x00402f0b:	pushl %eax
0x00402f0c:	call GetStdHandle@kernel32.dll
GetStdHandle@kernel32.dll: API Node	
0x00402f12:	movl %edi, %eax
0x00402f14:	cmpl %edi, $0xffffffff<UINT8>
0x00402f17:	je 63
0x00402f19:	pushl %edi
0x00402f1a:	call GetFileType@kernel32.dll
GetFileType@kernel32.dll: API Node	
0x00402f20:	testl %eax, %eax
0x00402f22:	je 52
0x00402f24:	andl %eax, $0xff<UINT32>
0x00402f29:	cmpl %eax, $0x2<UINT8>
0x00402f2c:	movl (%esi), %edi
0x00402f2e:	jne 0x00402f36
0x00402f36:	cmpl %eax, $0x3<UINT8>
0x00402f39:	jne 4
0x00402f3b:	orb 0x4(%esi), $0x8<UINT8>
0x00402f3f:	leal %eax, 0xc(%esi)
0x00402f42:	pushl $0xfa0<UINT32>
0x00402f47:	pushl %eax
0x00402f48:	call 0x0040552d
0x00402f4d:	testl %eax, %eax
0x00402f4f:	popl %ecx
0x00402f50:	popl %ecx
0x00402f51:	je -87
0x00402f53:	incl 0x8(%esi)
0x00402f56:	jmp 0x00402f62
0x00402f62:	incl %ebx
0x00402f63:	cmpl %ebx, $0x3<UINT8>
0x00402f66:	jl 0x00402ede
0x00402f01:	movl %eax, %ebx
0x00402f03:	decl %eax
0x00402f04:	negl %eax
0x00402f06:	sbbl %eax, %eax
0x00402f08:	addl %eax, $0xfffffff5<UINT8>
0x00402f6c:	pushl 0x40b2c8
0x00402f72:	call LockResource@kernel32.dll
LockResource@kernel32.dll: API Node	
0x00402f78:	xorl %eax, %eax
0x00402f7a:	popl %edi
0x00402f7b:	popl %esi
0x00402f7c:	popl %ebp
0x00402f7d:	popl %ebx
0x00402f7e:	addl %esp, $0x48<UINT8>
0x00402f81:	ret

0x00401359:	testl %eax, %eax
0x0040135b:	jnl 0x00401365
0x00401365:	call GetCommandLineA@kernel32.dll
GetCommandLineA@kernel32.dll: API Node	
0x0040136b:	movl 0x40c404, %eax
0x00401370:	call 0x00402c62
0x00402c62:	pushl %ecx
0x00402c63:	pushl %ecx
0x00402c64:	movl %eax, 0x40ad40
0x00402c69:	pushl %ebx
0x00402c6a:	pushl %ebp
0x00402c6b:	pushl %esi
0x00402c6c:	pushl %edi
0x00402c6d:	movl %edi, 0x40807c
0x00402c73:	xorl %ebx, %ebx
0x00402c75:	xorl %esi, %esi
0x00402c77:	cmpl %eax, %ebx
0x00402c79:	pushl $0x2<UINT8>
0x00402c7b:	popl %ebp
0x00402c7c:	jne 45
0x00402c7e:	call GetEnvironmentStringsW@kernel32.dll
GetEnvironmentStringsW@kernel32.dll: API Node	
0x00402c80:	movl %esi, %eax
0x00402c82:	cmpl %esi, %ebx
0x00402c84:	je 12
0x00402c86:	movl 0x40ad40, $0x1<UINT32>
0x00402c90:	jmp 0x00402cb0
0x00402cb0:	cmpl %esi, %ebx
0x00402cb2:	jne 0x00402cbc
0x00402cbc:	cmpw (%esi), %bx
0x00402cbf:	movl %eax, %esi
0x00402cc1:	je 0x00402cd1
0x00402cd1:	movl %edi, 0x408078
0x00402cd7:	pushl %ebx
0x00402cd8:	pushl %ebx
0x00402cd9:	pushl %ebx
0x00402cda:	subl %eax, %esi
0x00402cdc:	pushl %ebx
0x00402cdd:	sarl %eax
0x00402cdf:	incl %eax
0x00402ce0:	pushl %eax
0x00402ce1:	pushl %esi
0x00402ce2:	pushl %ebx
0x00402ce3:	pushl %ebx
0x00402ce4:	movl 0x34(%esp), %eax
0x00402ce8:	call WideCharToMultiByte@kernel32.dll
WideCharToMultiByte@kernel32.dll: API Node	
0x00402cea:	movl %ebp, %eax
0x00402cec:	cmpl %ebp, %ebx
0x00402cee:	je 50
0x00402cf0:	pushl %ebp
0x00402cf1:	call 0x004034ec
0x00402cf6:	cmpl %eax, %ebx
0x00402cf8:	popl %ecx
0x00402cf9:	movl 0x10(%esp), %eax
0x00402cfd:	je 35
0x00402cff:	pushl %ebx
0x00402d00:	pushl %ebx
0x00402d01:	pushl %ebp
0x00402d02:	pushl %eax
0x00402d03:	pushl 0x24(%esp)
0x00402d07:	pushl %esi
0x00402d08:	pushl %ebx
0x00402d09:	pushl %ebx
0x00402d0a:	call WideCharToMultiByte@kernel32.dll
0x00402d0c:	testl %eax, %eax
0x00402d0e:	jne 0x00402d1e
0x00402d1e:	movl %ebx, 0x10(%esp)
0x00402d22:	pushl %esi
0x00402d23:	call FreeEnvironmentStringsW@kernel32.dll
FreeEnvironmentStringsW@kernel32.dll: API Node	
0x00402d29:	movl %eax, %ebx
0x00402d2b:	jmp 0x00402d7d
0x00402d7d:	popl %edi
0x00402d7e:	popl %esi
0x00402d7f:	popl %ebp
0x00402d80:	popl %ebx
0x00402d81:	popl %ecx
0x00402d82:	popl %ecx
0x00402d83:	ret

0x00401375:	movl 0x40abc0, %eax
0x0040137a:	call 0x00402bc0
0x00402bc0:	pushl %ebp
0x00402bc1:	movl %ebp, %esp
0x00402bc3:	pushl %ecx
0x00402bc4:	pushl %ecx
0x00402bc5:	pushl %ebx
0x00402bc6:	pushl %esi
0x00402bc7:	pushl %edi
0x00402bc8:	xorl %edi, %edi
0x00402bca:	cmpl 0x40b3ec, %edi
0x00402bd0:	jne 5
0x00402bd2:	call 0x00404202
0x00404202:	cmpl 0x40b3ec, $0x0<UINT8>
0x00404209:	jne 0x0040421d
0x0040420b:	pushl $0xfffffffd<UINT8>
0x0040420d:	call 0x004040b2
0x004040b2:	pushl $0x14<UINT8>
0x004040b4:	pushl $0x408dd0<UINT32>
0x004040b9:	call 0x00401e80
0x004040be:	orl -32(%ebp), $0xffffffff<UINT8>
0x004040c2:	pushl $0xd<UINT8>
0x004040c4:	call 0x00403414
0x00403414:	pushl %ebp
0x00403415:	movl %ebp, %esp
0x00403417:	movl %eax, 0x8(%ebp)
0x0040341a:	pushl %esi
0x0040341b:	leal %esi, 0x40a590(,%eax,8)
0x00403422:	cmpl (%esi), $0x0<UINT8>
0x00403425:	jne 0x0040343a
0x0040343a:	pushl (%esi)
0x0040343c:	call EnterCriticalSection@kernel32.dll
EnterCriticalSection@kernel32.dll: API Node	
0x00403442:	popl %esi
0x00403443:	popl %ebp
0x00403444:	ret

0x004040c9:	popl %ecx
0x004040ca:	xorl %edi, %edi
0x004040cc:	movl -4(%ebp), %edi
0x004040cf:	movl 0x40aef0, %edi
0x004040d5:	movl %eax, 0x8(%ebp)
0x004040d8:	cmpl %eax, $0xfffffffe<UINT8>
0x004040db:	jne 0x004040ef
0x004040ef:	cmpl %eax, $0xfffffffd<UINT8>
0x004040f2:	jne 18
0x004040f4:	movl 0x40aef0, $0x1<UINT32>
0x004040fe:	call GetACP@kernel32.dll
GetACP@kernel32.dll: API Node	
0x00404104:	jmp 0x0040411a
0x0040411a:	movl 0x8(%ebp), %eax
0x0040411d:	cmpl %eax, 0x40b1a4
0x00404123:	je 187
0x00404129:	movl %esi, 0x40b090
0x0040412f:	movl -36(%ebp), %esi
0x00404132:	cmpl %esi, %edi
0x00404134:	je 0x0040413a
0x0040413a:	pushl $0x220<UINT32>
0x0040413f:	call 0x004034ec
0x00404144:	popl %ecx
0x00404145:	movl %esi, %eax
0x00404147:	movl -36(%ebp), %esi
0x0040414a:	cmpl %esi, %edi
0x0040414c:	je 127
0x0040414e:	pushl 0x8(%ebp)
0x00404151:	call 0x00403f1e
0x00403f1e:	pushl %ebp
0x00403f1f:	movl %ebp, %esp
0x00403f21:	subl %esp, $0x1c<UINT8>
0x00403f24:	movl %eax, 0x40a6d0
0x00403f29:	pushl %ebx
0x00403f2a:	pushl %esi
0x00403f2b:	movl %esi, 0x8(%ebp)
0x00403f2e:	xorl %eax, %ebp
0x00403f30:	xorl %ebx, %ebx
0x00403f32:	cmpl %esi, %ebx
0x00403f34:	movl -4(%ebp), %eax
0x00403f37:	pushl %edi
0x00403f38:	je 340
0x00403f3e:	xorl %edx, %edx
0x00403f40:	xorl %eax, %eax
0x00403f42:	cmpl 0x40a890(%eax), %esi
0x00403f48:	je 101
0x00403f4a:	addl %eax, $0x30<UINT8>
0x00403f4d:	incl %edx
0x00403f4e:	cmpl %eax, $0xf0<UINT32>
0x00403f53:	jb 0x00403f42
0x00403f55:	leal %eax, -24(%ebp)
0x00403f58:	pushl %eax
0x00403f59:	pushl %esi
0x00403f5a:	call GetCPInfo@kernel32.dll
GetCPInfo@kernel32.dll: API Node	
0x00403f60:	cmpl %eax, $0x1<UINT8>
0x00403f63:	jne 289
0x00403f69:	pushl $0x40<UINT8>
0x00403f6b:	xorl %eax, %eax
0x00403f6d:	cmpl -24(%ebp), $0x1<UINT8>
0x00403f71:	popl %ecx
0x00403f72:	movl %edi, $0x40b0a0<UINT32>
0x00403f77:	rep stosl %es:(%edi), %eax
0x00403f79:	stosb %es:(%edi), %al
0x00403f7a:	movl 0x40b1a4, %esi
0x00403f80:	movl 0x40b08c, %ebx
0x00403f86:	jbe 236
0x00403f8c:	cmpb -18(%ebp), $0x0<UINT8>
0x00403f90:	je 0x00404050
0x00404050:	xorl %ecx, %ecx
0x00404052:	incl %ecx
0x00404053:	movl %eax, %ecx
0x00404055:	orb 0x40b0a1(%eax), $0x8<UINT8>
0x0040405c:	incl %eax
0x0040405d:	cmpl %eax, $0xff<UINT32>
0x00404062:	jb 0x00404055
0x00404064:	movl %eax, %esi
0x00404066:	call 0x00403d2b
0x00403d2b:	subl %eax, $0x3a4<UINT32>
0x00403d30:	je 34
0x00403d32:	subl %eax, $0x4<UINT8>
0x00403d35:	je 23
0x00403d37:	subl %eax, $0xd<UINT8>
0x00403d3a:	je 12
0x00403d3c:	decl %eax
0x00403d3d:	je 3
0x00403d3f:	xorl %eax, %eax
0x00403d41:	ret

0x0040406b:	movl 0x40b08c, %eax
0x00404070:	movl 0x40b094, %ecx
0x00404076:	jmp 0x0040407e
0x0040407e:	xorl %eax, %eax
0x00404080:	movl %edi, $0x40b1b0<UINT32>
0x00404085:	stosl %es:(%edi), %eax
0x00404086:	stosl %es:(%edi), %eax
0x00404087:	stosl %es:(%edi), %eax
0x00404088:	jmp 0x00404097
0x00404097:	call 0x00403d83
0x00403d83:	pushl %ebp
0x00403d84:	leal %ebp, -1176(%esp)
0x00403d8b:	subl %esp, $0x518<UINT32>
0x00403d91:	movl %eax, 0x40a6d0
0x00403d96:	xorl %eax, %ebp
0x00403d98:	movl 0x494(%ebp), %eax
0x00403d9e:	pushl %esi
0x00403d9f:	leal %eax, -128(%ebp)
0x00403da2:	pushl %eax
0x00403da3:	pushl 0x40b1a4
0x00403da9:	call GetCPInfo@kernel32.dll
0x00403daf:	cmpl %eax, $0x1<UINT8>
0x00403db2:	movl %esi, $0x100<UINT32>
0x00403db7:	jne 263
0x00403dbd:	xorl %eax, %eax
0x00403dbf:	movb 0x394(%ebp,%eax), %al
0x00403dc6:	incl %eax
0x00403dc7:	cmpl %eax, %esi
0x00403dc9:	jb 0x00403dbf
0x00403dcb:	movb %al, -122(%ebp)
0x00403dce:	testb %al, %al
0x00403dd0:	movb 0x394(%ebp), $0x20<UINT8>
0x00403dd7:	je 0x00403e0f
0x00403e0f:	pushl $0x0<UINT8>
0x00403e11:	pushl 0x40b08c
0x00403e17:	leal %eax, -108(%ebp)
0x00403e1a:	pushl 0x40b1a4
0x00403e20:	pushl %eax
0x00403e21:	pushl %esi
0x00403e22:	leal %eax, 0x394(%ebp)
0x00403e28:	pushl %eax
0x00403e29:	pushl $0x1<UINT8>
0x00403e2b:	call 0x004060f8
0x004060f8:	pushl $0x1c<UINT8>
0x004060fa:	pushl $0x4091b8<UINT32>
0x004060ff:	call 0x00401e80
0x00406104:	xorl %esi, %esi
0x00406106:	cmpl 0x40af68, %esi
0x0040610c:	jne 53
0x0040610e:	leal %eax, -28(%ebp)
0x00406111:	pushl %eax
0x00406112:	xorl %edi, %edi
0x00406114:	incl %edi
0x00406115:	pushl %edi
0x00406116:	pushl $0x4091b0<UINT32>
0x0040611b:	pushl %edi
0x0040611c:	call GetStringTypeW@kernel32.dll
GetStringTypeW@kernel32.dll: API Node	
0x00406122:	testl %eax, %eax
0x00406124:	je 8
0x00406126:	movl 0x40af68, %edi
0x0040612c:	jmp 0x00406143
0x00406143:	movl %eax, 0x40af68
0x00406148:	cmpl %eax, $0x2<UINT8>
0x0040614b:	je 234
0x00406151:	cmpl %eax, %esi
0x00406153:	je 226
0x00406159:	cmpl %eax, $0x1<UINT8>
0x0040615c:	jne 255
0x00406162:	movl -36(%ebp), %esi
0x00406165:	movl -32(%ebp), %esi
0x00406168:	cmpl 0x18(%ebp), %esi
0x0040616b:	jne 0x00406175
0x00406175:	pushl %esi
0x00406176:	pushl %esi
0x00406177:	pushl 0x10(%ebp)
0x0040617a:	pushl 0xc(%ebp)
0x0040617d:	xorl %eax, %eax
0x0040617f:	cmpl 0x20(%ebp), %esi
0x00406182:	setne %al
0x00406185:	leal %eax, 0x1(,%eax,8)
0x0040618c:	pushl %eax
0x0040618d:	pushl 0x18(%ebp)
0x00406190:	call MultiByteToWideChar@kernel32.dll
MultiByteToWideChar@kernel32.dll: API Node	
0x00406196:	movl %edi, %eax
0x00406198:	movl -40(%ebp), %edi
0x0040619b:	testl %edi, %edi
0x0040619d:	je 190
0x004061a3:	andl -4(%ebp), $0x0<UINT8>
0x004061a7:	leal %ebx, (%edi,%edi)
0x004061aa:	movl %eax, %ebx
0x004061ac:	addl %eax, $0x3<UINT8>
0x004061af:	andl %eax, $0xfffffffc<UINT8>
0x004061b2:	call 0x00403080
0x004061b7:	movl -24(%ebp), %esp
0x004061ba:	movl %esi, %esp
0x004061bc:	movl -44(%ebp), %esi
0x004061bf:	pushl %ebx
0x004061c0:	pushl $0x0<UINT8>
0x004061c2:	pushl %esi
0x004061c3:	call 0x00404e50
0x00404e50:	movl %edx, 0xc(%esp)
0x00404e54:	movl %ecx, 0x4(%esp)
0x00404e58:	testl %edx, %edx
0x00404e5a:	je 79
0x00404e5c:	xorl %eax, %eax
0x00404e5e:	movb %al, 0x8(%esp)
0x00404e62:	pushl %edi
0x00404e63:	movl %edi, %ecx
0x00404e65:	cmpl %edx, $0x4<UINT8>
0x00404e68:	jb 49
0x00404e6a:	negl %ecx
0x00404e6c:	andl %ecx, $0x3<UINT8>
0x00404e6f:	je 0x00404e7d
0x00404e7d:	movl %ecx, %eax
0x00404e7f:	shll %eax, $0x8<UINT8>
0x00404e82:	addl %eax, %ecx
0x00404e84:	movl %ecx, %eax
0x00404e86:	shll %eax, $0x10<UINT8>
0x00404e89:	addl %eax, %ecx
0x00404e8b:	movl %ecx, %edx
0x00404e8d:	andl %edx, $0x3<UINT8>
0x00404e90:	shrl %ecx, $0x2<UINT8>
0x00404e93:	je 6
0x00404e95:	rep stosl %es:(%edi), %eax
0x00404e97:	testl %edx, %edx
0x00404e99:	je 0x00404ea5
0x00404ea5:	movl %eax, 0x8(%esp)
0x00404ea9:	popl %edi
0x00404eaa:	ret

0x004061c8:	addl %esp, $0xc<UINT8>
0x004061cb:	orl -4(%ebp), $0xffffffff<UINT8>
0x004061cf:	jmp 0x004061e6
0x004061e6:	testl %esi, %esi
0x004061e8:	jne 0x00406201
0x00406201:	pushl %edi
0x00406202:	pushl %esi
0x00406203:	pushl 0x10(%ebp)
0x00406206:	pushl 0xc(%ebp)
0x00406209:	pushl $0x1<UINT8>
0x0040620b:	pushl 0x18(%ebp)
0x0040620e:	call MultiByteToWideChar@kernel32.dll
0x00406214:	testl %eax, %eax
0x00406216:	je 17
0x00406218:	pushl 0x14(%ebp)
0x0040621b:	pushl %eax
0x0040621c:	pushl %esi
0x0040621d:	pushl 0x8(%ebp)
0x00406220:	call GetStringTypeW@kernel32.dll
0x00406226:	movl -36(%ebp), %eax
0x00406229:	cmpl -32(%ebp), $0x0<UINT8>
0x0040622d:	je 0x00406236
0x00406236:	movl %eax, -36(%ebp)
0x00406239:	jmp 0x004062a9
0x004062a9:	leal %esp, -56(%ebp)
0x004062ac:	call 0x00401ebb
0x004062b1:	ret

0x00403e30:	pushl $0x0<UINT8>
0x00403e32:	pushl 0x40b1a4
0x00403e38:	leal %eax, 0x294(%ebp)
0x00403e3e:	pushl %esi
0x00403e3f:	pushl %eax
0x00403e40:	pushl %esi
0x00403e41:	leal %eax, 0x394(%ebp)
0x00403e47:	pushl %eax
0x00403e48:	pushl %esi
0x00403e49:	pushl 0x40b08c
0x00403e4f:	call 0x00406340
0x00406340:	pushl $0x3c<UINT8>
0x00406342:	pushl $0x4091c8<UINT32>
0x00406347:	call 0x00401e80
0x0040634c:	xorl %ebx, %ebx
0x0040634e:	cmpl 0x40af90, %ebx
0x00406354:	jne 0x0040638e
0x00406356:	pushl %ebx
0x00406357:	pushl %ebx
0x00406358:	xorl %esi, %esi
0x0040635a:	incl %esi
0x0040635b:	pushl %esi
0x0040635c:	pushl $0x4091b0<UINT32>
0x00406361:	pushl $0x100<UINT32>
0x00406366:	pushl %ebx
0x00406367:	call LCMapStringW@kernel32.dll
LCMapStringW@kernel32.dll: API Node	
0x0040636d:	testl %eax, %eax
0x0040636f:	je 8
0x00406371:	movl 0x40af90, %esi
0x00406377:	jmp 0x0040638e
0x0040638e:	cmpl 0x14(%ebp), %ebx
0x00406391:	jle 27
0x00406393:	movl %ecx, 0x14(%ebp)
0x00406396:	movl %eax, 0x10(%ebp)
0x00406399:	decl %ecx
0x0040639a:	cmpb (%eax), %bl
0x0040639c:	je 8
0x0040639e:	incl %eax
0x0040639f:	cmpl %ecx, %ebx
0x004063a1:	jne 0x00406399
0x004063a3:	orl %ecx, $0xffffffff<UINT8>
0x004063a6:	orl %eax, $0xffffffff<UINT8>
0x004063a9:	subl %eax, %ecx
0x004063ab:	addl 0x14(%ebp), %eax
0x004063ae:	movl %eax, 0x40af90
0x004063b3:	cmpl %eax, $0x2<UINT8>
0x004063b6:	je 476
0x004063bc:	cmpl %eax, %ebx
0x004063be:	je 468
0x004063c4:	cmpl %eax, $0x1<UINT8>
0x004063c7:	jne 513
0x004063cd:	xorl %edi, %edi
0x004063cf:	movl -44(%ebp), %edi
0x004063d2:	movl -60(%ebp), %ebx
0x004063d5:	movl -56(%ebp), %ebx
0x004063d8:	cmpl 0x20(%ebp), %ebx
0x004063db:	jne 0x004063e5
0x004063e5:	pushl %ebx
0x004063e6:	pushl %ebx
0x004063e7:	pushl 0x14(%ebp)
0x004063ea:	pushl 0x10(%ebp)
0x004063ed:	xorl %eax, %eax
0x004063ef:	cmpl 0x24(%ebp), %ebx
0x004063f2:	setne %al
0x004063f5:	leal %eax, 0x1(,%eax,8)
0x004063fc:	pushl %eax
0x004063fd:	pushl 0x20(%ebp)
0x00406400:	call MultiByteToWideChar@kernel32.dll
0x00406406:	movl %esi, %eax
0x00406408:	movl -52(%ebp), %esi
0x0040640b:	cmpl %esi, %ebx
0x0040640d:	je 443
0x00406413:	movl -4(%ebp), $0x1<UINT32>
0x0040641a:	leal %eax, (%esi,%esi)
0x0040641d:	addl %eax, $0x3<UINT8>
0x00406420:	andl %eax, $0xfffffffc<UINT8>
0x00406423:	call 0x00403080
0x00406428:	movl -24(%ebp), %esp
0x0040642b:	movl %eax, %esp
0x0040642d:	movl -28(%ebp), %eax
0x00406430:	orl -4(%ebp), $0xffffffff<UINT8>
0x00406434:	jmp 0x00406451
0x00406451:	cmpl -28(%ebp), %ebx
0x00406454:	jne 0x00406472
0x00406472:	pushl %esi
0x00406473:	pushl -28(%ebp)
0x00406476:	pushl 0x14(%ebp)
0x00406479:	pushl 0x10(%ebp)
0x0040647c:	pushl $0x1<UINT8>
0x0040647e:	pushl 0x20(%ebp)
0x00406481:	call MultiByteToWideChar@kernel32.dll
0x00406487:	testl %eax, %eax
0x00406489:	je 230
0x0040648f:	pushl %ebx
0x00406490:	pushl %ebx
0x00406491:	pushl %esi
0x00406492:	pushl -28(%ebp)
0x00406495:	pushl 0xc(%ebp)
0x00406498:	pushl 0x8(%ebp)
0x0040649b:	call LCMapStringW@kernel32.dll
0x004064a1:	movl %edi, %eax
0x004064a3:	movl -44(%ebp), %edi
0x004064a6:	cmpl %edi, %ebx
0x004064a8:	je 199
0x004064ae:	testb 0xd(%ebp), $0x4<UINT8>
0x004064b2:	je 0x004064e1
0x004064e1:	movl -4(%ebp), $0x2<UINT32>
0x004064e8:	leal %eax, (%edi,%edi)
0x004064eb:	addl %eax, $0x3<UINT8>
0x004064ee:	andl %eax, $0xfffffffc<UINT8>
0x004064f1:	call 0x00403080
0x004064f6:	movl -24(%ebp), %esp
0x004064f9:	movl %eax, %esp
0x004064fb:	movl -32(%ebp), %eax
0x004064fe:	orl -4(%ebp), $0xffffffff<UINT8>
0x00406502:	jmp 0x0040651f
0x0040651f:	cmpl -32(%ebp), %ebx
0x00406522:	jne 0x0040653c
0x0040653c:	pushl %edi
0x0040653d:	pushl -32(%ebp)
0x00406540:	pushl %esi
0x00406541:	pushl -28(%ebp)
0x00406544:	pushl 0xc(%ebp)
0x00406547:	pushl 0x8(%ebp)
0x0040654a:	call LCMapStringW@kernel32.dll
0x00406550:	testl %eax, %eax
0x00406552:	je 33
0x00406554:	pushl %ebx
0x00406555:	pushl %ebx
0x00406556:	cmpl 0x1c(%ebp), %ebx
0x00406559:	jne 0x0040655f
0x0040655f:	pushl 0x1c(%ebp)
0x00406562:	pushl 0x18(%ebp)
0x00406565:	pushl %edi
0x00406566:	pushl -32(%ebp)
0x00406569:	pushl %ebx
0x0040656a:	pushl 0x20(%ebp)
0x0040656d:	call WideCharToMultiByte@kernel32.dll
0x00406573:	movl %edi, %eax
0x00406575:	cmpl -56(%ebp), %ebx
0x00406578:	je 0x00406583
0x00406583:	cmpl -60(%ebp), %ebx
0x00406586:	je 0x00406591
0x00406591:	movl %eax, %edi
0x00406593:	jmp 0x0040670d
0x0040670d:	leal %esp, -88(%ebp)
0x00406710:	call 0x00401ebb
0x00406715:	ret

0x00403e54:	pushl $0x0<UINT8>
0x00403e56:	pushl 0x40b1a4
0x00403e5c:	leal %eax, 0x194(%ebp)
0x00403e62:	pushl %esi
0x00403e63:	pushl %eax
0x00403e64:	pushl %esi
0x00403e65:	leal %eax, 0x394(%ebp)
0x00403e6b:	pushl %eax
0x00403e6c:	pushl $0x200<UINT32>
0x00403e71:	pushl 0x40b08c
0x00403e77:	call 0x00406340
0x00403e7c:	addl %esp, $0x5c<UINT8>
0x00403e7f:	xorl %eax, %eax
0x00403e81:	movw %cx, -108(%ebp,%eax,2)
0x00403e86:	testb %cl, $0x1<UINT8>
0x00403e89:	je 0x00403ea1
0x00403ea1:	testb %cl, $0x2<UINT8>
0x00403ea4:	je 0x00403eb6
0x00403eb6:	movb 0x40b1c0(%eax), $0x0<UINT8>
0x00403ebd:	incl %eax
0x00403ebe:	cmpl %eax, %esi
0x00403ec0:	jb 0x00403e81
0x00403e8b:	orb 0x40b0a1(%eax), $0x10<UINT8>
0x00403e92:	movb %cl, 0x294(%ebp,%eax)
0x00403e99:	movb 0x40b1c0(%eax), %cl
0x00403e9f:	jmp 0x00403ebd
0x00403ec2:	jmp 0x00403f08
0x00403f08:	movl %ecx, 0x494(%ebp)
0x00403f0e:	xorl %ecx, %ebp
0x00403f10:	popl %esi
0x00403f11:	call 0x00403793
0x00403793:	cmpl %ecx, 0x40a6d0
0x00403799:	jne 1
0x0040379b:	ret

0x00403f16:	addl %ebp, $0x498<UINT32>
0x00403f1c:	leave
0x00403f1d:	ret

0x0040409c:	xorl %eax, %eax
0x0040409e:	jmp 0x004040a3
0x004040a3:	movl %ecx, -4(%ebp)
0x004040a6:	popl %edi
0x004040a7:	popl %esi
0x004040a8:	xorl %ecx, %ebp
0x004040aa:	popl %ebx
0x004040ab:	call 0x00403793
0x004040b0:	leave
0x004040b1:	ret

0x00404156:	popl %ecx
0x00404157:	movl -32(%ebp), %eax
0x0040415a:	cmpl %eax, %edi
0x0040415c:	jne 111
0x0040415e:	movl (%esi), %edi
0x00404160:	movl %eax, 0x40b1a4
0x00404165:	movl 0x4(%esi), %eax
0x00404168:	movl %eax, 0x40b094
0x0040416d:	movl 0x8(%esi), %eax
0x00404170:	movl %eax, 0x40b08c
0x00404175:	movl 0xc(%esi), %eax
0x00404178:	xorl %eax, %eax
0x0040417a:	movl -28(%ebp), %eax
0x0040417d:	cmpl %eax, $0x5<UINT8>
0x00404180:	jnl 0x00404192
0x00404182:	movw %cx, 0x40b1b0(,%eax,2)
0x0040418a:	movw 0x10(%esi,%eax,2), %cx
0x0040418f:	incl %eax
0x00404190:	jmp 0x0040417a
0x00404192:	xorl %eax, %eax
0x00404194:	movl -28(%ebp), %eax
0x00404197:	cmpl %eax, $0x101<UINT32>
0x0040419c:	jnl 0x004041ab
0x0040419e:	movb %cl, 0x40b0a0(%eax)
0x004041a4:	movb 0x1c(%eax,%esi), %cl
0x004041a8:	incl %eax
0x004041a9:	jmp 0x00404194
0x004041ab:	xorl %eax, %eax
0x004041ad:	movl -28(%ebp), %eax
0x004041b0:	cmpl %eax, $0x100<UINT32>
0x004041b5:	jnl 0x004041c7
0x004041b7:	movb %cl, 0x40b1c0(%eax)
0x004041bd:	movb 0x11d(%eax,%esi), %cl
0x004041c4:	incl %eax
0x004041c5:	jmp 0x004041ad
0x004041c7:	movl 0x40b090, %esi
0x004041cd:	cmpl -32(%ebp), $0xffffffff<UINT8>
0x004041d1:	jne 0x004041e7
0x004041e7:	orl -4(%ebp), $0xffffffff<UINT8>
0x004041eb:	call 0x004041f9
0x004041f9:	pushl $0xd<UINT8>
0x004041fb:	call 0x0040335f
0x0040335f:	pushl %ebp
0x00403360:	movl %ebp, %esp
0x00403362:	movl %eax, 0x8(%ebp)
0x00403365:	pushl 0x40a590(,%eax,8)
0x0040336c:	call LeaveCriticalSection@kernel32.dll
LeaveCriticalSection@kernel32.dll: API Node	
0x00403372:	popl %ebp
0x00403373:	ret

0x00404200:	popl %ecx
0x00404201:	ret

0x004041f0:	movl %eax, -32(%ebp)
0x004041f3:	call 0x00401ebb
0x004041f8:	ret

0x00404212:	popl %ecx
0x00404213:	movl 0x40b3ec, $0x1<UINT32>
0x0040421d:	xorl %eax, %eax
0x0040421f:	ret

0x00402bd7:	pushl $0x104<UINT32>
0x00402bdc:	movl %esi, $0x40ac38<UINT32>
0x00402be1:	pushl %esi
0x00402be2:	pushl %edi
0x00402be3:	movb 0x40ad3c, $0x0<UINT8>
0x00402bea:	call GetModuleFileNameA@kernel32.dll
GetModuleFileNameA@kernel32.dll: API Node	
0x00402bf0:	movl %eax, 0x40c404
0x00402bf5:	cmpl %eax, %edi
0x00402bf7:	movl 0x40ac1c, %esi
0x00402bfd:	je 7
0x00402bff:	cmpb (%eax), $0x0<UINT8>
0x00402c02:	movl %ebx, %eax
0x00402c04:	jne 0x00402c08
0x00402c08:	leal %eax, -4(%ebp)
0x00402c0b:	pushl %eax
0x00402c0c:	pushl %edi
0x00402c0d:	leal %esi, -8(%ebp)
0x00402c10:	xorl %ecx, %ecx
0x00402c12:	movl %eax, %ebx
0x00402c14:	call 0x00402a54
0x00402a54:	pushl %ebp
0x00402a55:	movl %ebp, %esp
0x00402a57:	pushl %ecx
0x00402a58:	pushl %ebx
0x00402a59:	movl %ebx, 0xc(%ebp)
0x00402a5c:	xorl %edx, %edx
0x00402a5e:	cmpl 0x8(%ebp), %edx
0x00402a61:	pushl %edi
0x00402a62:	movl (%esi), %edx
0x00402a64:	movl %edi, %ecx
0x00402a66:	movl (%ebx), $0x1<UINT32>
0x00402a6c:	je 0x00402a77
0x00402a77:	cmpb (%eax), $0x22<UINT8>
0x00402a7a:	jne 0x00402a8a
0x00402a7c:	xorl %ecx, %ecx
0x00402a7e:	testl %edx, %edx
0x00402a80:	sete %cl
0x00402a83:	incl %eax
0x00402a84:	movl %edx, %ecx
0x00402a86:	movb %cl, $0x22<UINT8>
0x00402a88:	jmp 0x00402ab7
0x00402ab7:	testl %edx, %edx
0x00402ab9:	jne 0x00402a77
0x00402a8a:	incl (%esi)
0x00402a8c:	testl %edi, %edi
0x00402a8e:	je 0x00402a95
0x00402a95:	movb %cl, (%eax)
0x00402a97:	movzbl %ebx, %cl
0x00402a9a:	incl %eax
0x00402a9b:	testb 0x40b0a1(%ebx), $0x4<UINT8>
0x00402aa2:	je 0x00402ab0
0x00402ab0:	testb %cl, %cl
0x00402ab2:	movl %ebx, 0xc(%ebp)
0x00402ab5:	je 0x00402ae9
0x00402abb:	cmpb %cl, $0x20<UINT8>
0x00402abe:	je 5
0x00402ac0:	cmpb %cl, $0x9<UINT8>
0x00402ac3:	jne 0x00402a77
0x00402ae9:	decl %eax
0x00402aea:	jmp 0x00402acd
0x00402acd:	andl -4(%ebp), $0x0<UINT8>
0x00402ad1:	cmpb (%eax), $0x0<UINT8>
0x00402ad4:	je 0x00402bb0
0x00402bb0:	movl %eax, 0x8(%ebp)
0x00402bb3:	testl %eax, %eax
0x00402bb5:	je 0x00402bba
0x00402bba:	incl (%ebx)
0x00402bbc:	popl %edi
0x00402bbd:	popl %ebx
0x00402bbe:	leave
0x00402bbf:	ret

0x00402c19:	movl %esi, -4(%ebp)
0x00402c1c:	movl %eax, -8(%ebp)
0x00402c1f:	shll %esi, $0x2<UINT8>
0x00402c22:	addl %eax, %esi
0x00402c24:	pushl %eax
0x00402c25:	call 0x004034ec
0x00402c2a:	movl %edi, %eax
0x00402c2c:	addl %esp, $0xc<UINT8>
0x00402c2f:	testl %edi, %edi
0x00402c31:	jne 0x00402c38
0x00402c38:	leal %eax, -4(%ebp)
0x00402c3b:	pushl %eax
0x00402c3c:	leal %ecx, (%esi,%edi)
0x00402c3f:	pushl %edi
0x00402c40:	leal %esi, -8(%ebp)
0x00402c43:	movl %eax, %ebx
0x00402c45:	call 0x00402a54
0x00402a6e:	movl %ecx, 0x8(%ebp)
0x00402a71:	addl 0x8(%ebp), $0x4<UINT8>
0x00402a75:	movl (%ecx), %edi
0x00402a90:	movb %cl, (%eax)
0x00402a92:	movb (%edi), %cl
0x00402a94:	incl %edi
0x00402bb7:	andl (%eax), $0x0<UINT8>
0x00402c4a:	movl %eax, -4(%ebp)
0x00402c4d:	decl %eax
0x00402c4e:	popl %ecx
0x00402c4f:	movl 0x40ac00, %eax
0x00402c54:	popl %ecx
0x00402c55:	movl 0x40ac04, %edi
0x00402c5b:	xorl %eax, %eax
0x00402c5d:	popl %edi
0x00402c5e:	popl %esi
0x00402c5f:	popl %ebx
0x00402c60:	leave
0x00402c61:	ret

0x0040137f:	testl %eax, %eax
0x00401381:	jnl 0x0040138b
0x0040138b:	call 0x0040298d
0x0040298d:	pushl %ebx
0x0040298e:	xorl %ebx, %ebx
0x00402990:	cmpl 0x40b3ec, %ebx
0x00402996:	pushl %esi
0x00402997:	pushl %edi
0x00402998:	jne 0x0040299f
0x0040299f:	movl %esi, 0x40abc0
0x004029a5:	xorl %edi, %edi
0x004029a7:	cmpl %esi, %ebx
0x004029a9:	jne 0x004029bd
0x004029bd:	movb %al, (%esi)
0x004029bf:	cmpb %al, %bl
0x004029c1:	jne -22
0x004029c3:	leal %eax, 0x4(,%edi,4)
0x004029ca:	pushl %eax
0x004029cb:	call 0x004034ec
0x004029d0:	movl %edi, %eax
0x004029d2:	cmpl %edi, %ebx
0x004029d4:	popl %ecx
0x004029d5:	movl 0x40ac0c, %edi
0x004029db:	jne 0x004029e2
0x004029e2:	movl %esi, 0x40abc0
0x004029e8:	pushl %ebp
0x004029e9:	jmp 0x00402a15
0x00402a15:	cmpb (%esi), %bl
0x00402a17:	jne -46
0x00402a19:	pushl 0x40abc0
0x00402a1f:	call 0x0040260f
0x0040260f:	pushl $0xc<UINT8>
0x00402611:	pushl $0x408298<UINT32>
0x00402616:	call 0x00401e80
0x0040261b:	movl %esi, 0x8(%ebp)
0x0040261e:	testl %esi, %esi
0x00402620:	je 88
0x00402622:	cmpl 0x40b2c4, $0x3<UINT8>
0x00402629:	jne 0x0040266b
0x0040266b:	pushl %esi
0x0040266c:	pushl $0x0<UINT8>
0x0040266e:	pushl 0x40b2c0
0x00402674:	call HeapFree@kernel32.dll
HeapFree@kernel32.dll: API Node	
0x0040267a:	call 0x00401ebb
0x0040267f:	ret

0x00402a24:	movl 0x40abc0, %ebx
0x00402a2a:	movl (%edi), %ebx
0x00402a2c:	movl 0x40b3e0, $0x1<UINT32>
0x00402a36:	xorl %eax, %eax
0x00402a38:	popl %ecx
0x00402a39:	popl %ebp
0x00402a3a:	popl %edi
0x00402a3b:	popl %esi
0x00402a3c:	popl %ebx
0x00402a3d:	ret

0x00401390:	testl %eax, %eax
0x00401392:	jnl 0x0040139c
0x0040139c:	pushl $0x1<UINT8>
0x0040139e:	call 0x0040233e
0x0040233e:	movl %eax, 0x40b3f0
0x00402343:	testl %eax, %eax
0x00402345:	je 0x0040234e
0x0040234e:	pushl %esi
0x0040234f:	pushl %edi
0x00402350:	movl %ecx, $0x40a00c<UINT32>
0x00402355:	movl %edi, $0x40a01c<UINT32>
0x0040235a:	xorl %eax, %eax
0x0040235c:	cmpl %ecx, %edi
0x0040235e:	movl %esi, %ecx
0x00402360:	jae 23
0x00402362:	testl %eax, %eax
0x00402364:	jne 63
0x00402366:	movl %ecx, (%esi)
0x00402368:	testl %ecx, %ecx
0x0040236a:	je 0x0040236e
0x0040236e:	addl %esi, $0x4<UINT8>
0x00402371:	cmpl %esi, %edi
0x00402373:	jb 0x00402362
0x0040236c:	call 0x004042a0
0x00401422:	movl %eax, 0x40c400
0x00401427:	testl %eax, %eax
0x00401429:	pushl %esi
0x0040142a:	pushl $0x14<UINT8>
0x0040142c:	popl %esi
0x0040142d:	jne 7
0x0040142f:	movl %eax, $0x200<UINT32>
0x00401434:	jmp 0x0040143c
0x0040143c:	movl 0x40c400, %eax
0x00401441:	pushl $0x4<UINT8>
0x00401443:	pushl %eax
0x00401444:	call 0x00402705
0x00401449:	testl %eax, %eax
0x0040144b:	popl %ecx
0x0040144c:	popl %ecx
0x0040144d:	movl 0x40b3f4, %eax
0x00401452:	jne 0x00401472
0x00401472:	xorl %edx, %edx
0x00401474:	movl %ecx, $0x40a048<UINT32>
0x00401479:	jmp 0x00401480
0x00401480:	movl (%edx,%eax), %ecx
0x00401483:	addl %ecx, $0x20<UINT8>
0x00401486:	addl %edx, $0x4<UINT8>
0x00401489:	cmpl %ecx, $0x40a2c8<UINT32>
0x0040148f:	jl 0x0040147b
0x0040147b:	movl %eax, 0x40b3f4
0x00401491:	xorl %ecx, %ecx
0x00401493:	movl %edx, $0x40a058<UINT32>
0x00401498:	movl %esi, %ecx
0x0040149a:	movl %eax, %ecx
0x0040149c:	andl %eax, $0x1f<UINT8>
0x0040149f:	sarl %esi, $0x5<UINT8>
0x004014a2:	movl %esi, 0x40b2e0(,%esi,4)
0x004014a9:	leal %eax, (%eax,%eax,8)
0x004014ac:	movl %eax, (%esi,%eax,4)
0x004014af:	cmpl %eax, $0xffffffff<UINT8>
0x004014b2:	je 4
0x004014b4:	testl %eax, %eax
0x004014b6:	jne 0x004014bb
0x004014bb:	addl %edx, $0x20<UINT8>
0x004014be:	incl %ecx
0x004014bf:	cmpl %edx, $0x40a0b8<UINT32>
0x004014c5:	jl 0x00401498
0x004014c7:	xorl %eax, %eax
0x004014c9:	popl %esi
0x004014ca:	ret

0x004042a0:	pushl $0x80<UINT32>
0x004042a5:	call 0x004034ec
0x004042aa:	testl %eax, %eax
0x004042ac:	popl %ecx
0x004042ad:	movl 0x40b3e8, %eax
0x004042b2:	jne 0x004042b8
0x004042b8:	andl (%eax), $0x0<UINT8>
0x004042bb:	movl %eax, 0x40b3e8
0x004042c0:	movl 0x40b3e4, %eax
0x004042c5:	xorl %eax, %eax
0x004042c7:	ret

0x00402375:	testl %eax, %eax
0x00402377:	jne 44
0x00402379:	pushl $0x402fc6<UINT32>
0x0040237e:	call 0x00404300
0x00404300:	pushl 0x4(%esp)
0x00404304:	call 0x004042c8
0x004042c8:	pushl $0xc<UINT8>
0x004042ca:	pushl $0x408de0<UINT32>
0x004042cf:	call 0x00401e80
0x004042d4:	call 0x00402314
0x00402314:	pushl $0x8<UINT8>
0x00402316:	call 0x00403414
0x0040231b:	popl %ecx
0x0040231c:	ret

0x004042d9:	andl -4(%ebp), $0x0<UINT8>
0x004042dd:	movl %edi, 0x8(%ebp)
0x004042e0:	call 0x00404220
0x00404220:	pushl %esi
0x00404221:	pushl 0x40b3e8
0x00404227:	call 0x004068c8
0x004068c8:	pushl $0x10<UINT8>
0x004068ca:	pushl $0x409200<UINT32>
0x004068cf:	call 0x00401e80
0x004068d4:	cmpl 0x40b2c4, $0x3<UINT8>
0x004068db:	jne 0x00406917
0x00406917:	pushl 0x8(%ebp)
0x0040691a:	pushl $0x0<UINT8>
0x0040691c:	pushl 0x40b2c0
0x00406922:	call HeapSize@kernel32.dll
HeapSize@kernel32.dll: API Node	
0x00406928:	movl %esi, %eax
0x0040692a:	movl %eax, %esi
0x0040692c:	call 0x00401ebb
0x00406931:	ret

0x0040422c:	popl %ecx
0x0040422d:	movl %ecx, 0x40b3e4
0x00404233:	movl %esi, %eax
0x00404235:	movl %eax, 0x40b3e8
0x0040423a:	movl %edx, %ecx
0x0040423c:	subl %edx, %eax
0x0040423e:	addl %edx, $0x4<UINT8>
0x00404241:	cmpl %esi, %edx
0x00404243:	jae 0x00404293
0x00404293:	movl (%ecx), %edi
0x00404295:	addl 0x40b3e4, $0x4<UINT8>
0x0040429c:	movl %eax, %edi
0x0040429e:	popl %esi
0x0040429f:	ret

0x004042e5:	movl -28(%ebp), %eax
0x004042e8:	orl -4(%ebp), $0xffffffff<UINT8>
0x004042ec:	call 0x004042fa
0x004042fa:	call 0x0040231d
0x0040231d:	pushl $0x8<UINT8>
0x0040231f:	call 0x0040335f
0x00402324:	popl %ecx
0x00402325:	ret

0x004042ff:	ret

0x004042f1:	movl %eax, -28(%ebp)
0x004042f4:	call 0x00401ebb
0x004042f9:	ret

0x00404309:	negl %eax
0x0040430b:	sbbl %eax, %eax
0x0040430d:	negl %eax
0x0040430f:	popl %ecx
0x00404310:	decl %eax
0x00404311:	ret

0x00402383:	movl %esi, $0x40a000<UINT32>
0x00402388:	movl %eax, %esi
0x0040238a:	movl %edi, $0x40a008<UINT32>
0x0040238f:	cmpl %eax, %edi
0x00402391:	popl %ecx
0x00402392:	jae 15
0x00402394:	movl %eax, (%esi)
0x00402396:	testl %eax, %eax
0x00402398:	je 0x0040239c
0x0040239c:	addl %esi, $0x4<UINT8>
0x0040239f:	cmpl %esi, %edi
0x004023a1:	jb 0x00402394
0x0040239a:	call 0x00405ae4
0x00405ae4:	pushl %ebp
0x00405ae5:	movl %ebp, %esp
0x00405ae7:	subl %esp, $0x10<UINT8>
0x00405aea:	movl %eax, 0x40a6d0
0x00405aef:	testl %eax, %eax
0x00405af1:	je 7
0x00405af3:	cmpl %eax, $0xbb40e64e<UINT32>
0x00405af8:	jne 78
0x00405afa:	pushl %esi
0x00405afb:	leal %eax, -8(%ebp)
0x00405afe:	pushl %eax
0x00405aff:	call GetSystemTimeAsFileTime@kernel32.dll
GetSystemTimeAsFileTime@kernel32.dll: API Node	
0x00405b05:	movl %esi, -4(%ebp)
0x00405b08:	xorl %esi, -8(%ebp)
0x00405b0b:	call GetCurrentProcessId@kernel32.dll
GetCurrentProcessId@kernel32.dll: API Node	
0x00405b11:	xorl %esi, %eax
0x00405b13:	call GetCurrentThreadId@kernel32.dll
0x00405b19:	xorl %esi, %eax
0x00405b1b:	call GetTickCount@kernel32.dll
GetTickCount@kernel32.dll: API Node	
0x00405b21:	xorl %esi, %eax
0x00405b23:	leal %eax, -16(%ebp)
0x00405b26:	pushl %eax
0x00405b27:	call QueryPerformanceCounter@kernel32.dll
QueryPerformanceCounter@kernel32.dll: API Node	
0x00405b2d:	movl %eax, -12(%ebp)
0x00405b30:	xorl %eax, -16(%ebp)
0x00405b33:	xorl %esi, %eax
0x00405b35:	movl 0x40a6d0, %esi
0x00405b3b:	jne 0x00405b47
0x00405b47:	popl %esi
0x00405b48:	leave
0x00405b49:	ret

0x004023a3:	xorl %eax, %eax
0x004023a5:	popl %edi
0x004023a6:	popl %esi
0x004023a7:	ret

0x004013a3:	popl %ecx
0x004013a4:	movl -36(%ebp), %eax
0x004013a7:	cmpl %eax, %edi
0x004013a9:	je 0x004013b2
0x004013b2:	movl %eax, 0x40ac0c
0x004013b7:	movl 0x40ac10, %eax
0x004013bc:	pushl %eax
0x004013bd:	pushl 0x40ac04
0x004013c3:	pushl 0x40ac00
0x004013c9:	call 0x00401020
0x00401020:	pushl $0x408150<UINT32>
0x00401025:	call 0x00401052
0x00401052:	pushl $0x10<UINT8>
0x00401054:	pushl $0x408170<UINT32>
0x00401059:	call 0x00401e80
0x0040105e:	movl %esi, $0x40a068<UINT32>
0x00401063:	pushl %esi
0x00401064:	pushl $0x1<UINT8>
0x00401066:	call 0x0040150e
0x0040150e:	movl %eax, 0x4(%esp)
0x00401512:	cmpl %eax, $0x14<UINT8>
0x00401515:	jnl 11
0x00401517:	addl %eax, $0x10<UINT8>
0x0040151a:	pushl %eax
0x0040151b:	call 0x00403414
0x00401520:	popl %ecx
0x00401521:	ret

0x0040106b:	popl %ecx
0x0040106c:	popl %ecx
0x0040106d:	andl -4(%ebp), $0x0<UINT8>
0x00401071:	pushl %esi
0x00401072:	call 0x00401583
0x00401583:	pushl %esi
0x00401584:	movl %esi, 0x8(%esp)
0x00401588:	pushl 0x10(%esi)
0x0040158b:	call 0x004034fe
0x004034fe:	movl %eax, 0x4(%esp)
0x00403502:	cmpl %eax, 0x40b2c8
0x00403508:	jb 0x0040350d
0x0040350d:	movl %ecx, %eax
0x0040350f:	andl %eax, $0x1f<UINT8>
0x00403512:	sarl %ecx, $0x5<UINT8>
0x00403515:	movl %ecx, 0x40b2e0(,%ecx,4)
0x0040351c:	leal %eax, (%eax,%eax,8)
0x0040351f:	movsbl %eax, 0x4(%ecx,%eax,4)
0x00403524:	andl %eax, $0x40<UINT8>
0x00403527:	ret

0x00401590:	testl %eax, %eax
0x00401592:	popl %ecx
0x00401593:	je 0x00401607
0x00401607:	xorl %eax, %eax
0x00401609:	popl %esi
0x0040160a:	ret

0x00401077:	movl -28(%ebp), %eax
0x0040107a:	leal %eax, 0xc(%ebp)
0x0040107d:	pushl %eax
0x0040107e:	pushl 0x8(%ebp)
0x00401081:	pushl %esi
0x00401082:	call 0x004016c3
0x004016c3:	pushl %ebp
0x004016c4:	leal %ebp, -480(%esp)
0x004016cb:	subl %esp, $0x260<UINT32>
0x004016d1:	movl %eax, 0x40a6d0
0x004016d6:	movl %ecx, 0x1f0(%ebp)
0x004016dc:	xorl %eax, %ebp
0x004016de:	movl 0x1dc(%ebp), %eax
0x004016e4:	movl %eax, 0x1e8(%ebp)
0x004016ea:	movl -84(%ebp), %eax
0x004016ed:	movl %eax, 0x1ec(%ebp)
0x004016f3:	pushl %ebx
0x004016f4:	movb %bl, (%eax)
0x004016f6:	movl -52(%ebp), %ecx
0x004016f9:	xorl %ecx, %ecx
0x004016fb:	testb %bl, %bl
0x004016fd:	movl -80(%ebp), %eax
0x00401700:	movl -64(%ebp), %ecx
0x00401703:	movl -68(%ebp), %ecx
0x00401706:	movl -96(%ebp), %ecx
0x00401709:	je 1845
0x0040170f:	pushl %esi
0x00401710:	pushl %edi
0x00401711:	movl %edi, %eax
0x00401713:	jmp 0x00401718
0x00401718:	incl %edi
0x00401719:	cmpl -68(%ebp), $0x0<UINT8>
0x0040171d:	movl -80(%ebp), %edi
0x00401720:	jl 1820
0x00401726:	cmpb %bl, $0x20<UINT8>
0x00401729:	jl 0x0040173f
0x0040172b:	cmpb %bl, $0x78<UINT8>
0x0040172e:	jg 0x0040173f
0x00401730:	movsbl %eax, %bl
0x00401733:	movsbl %eax, 0x408180(%eax)
0x0040173a:	andl %eax, $0xf<UINT8>
0x0040173d:	jmp 0x00401741
0x00401741:	movsbl %eax, 0x4081a0(%ecx,%eax,8)
0x00401749:	pushl $0x7<UINT8>
0x0040174b:	sarl %eax, $0x4<UINT8>
0x0040174e:	popl %ecx
0x0040174f:	cmpl %eax, %ecx
0x00401751:	movl -112(%ebp), %eax
0x00401754:	ja 1755
0x0040175a:	jmp 0x004018df
0x004018df:	movl %ecx, 0x40a6c8
0x004018e5:	andl -92(%ebp), $0x0<UINT8>
0x004018e9:	movzbl %eax, %bl
0x004018ec:	testb 0x1(%ecx,%eax,2), $0xffffff80<UINT8>
0x004018f1:	je 0x00401906
0x00401906:	movl %ecx, -84(%ebp)
0x00401909:	leal %esi, -68(%ebp)
0x0040190c:	movb %al, %bl
0x0040190e:	call 0x00401635
0x00401635:	testb 0xc(%ecx), $0x40<UINT8>
0x00401639:	je 0x00401641
0x00401641:	decl 0x4(%ecx)
0x00401644:	js 0x00401651
0x00401651:	movsbl %eax, %al
0x00401654:	pushl %ecx
0x00401655:	pushl %eax
0x00401656:	call 0x00403528
0x00403528:	pushl %ebp
0x00403529:	movl %ebp, %esp
0x0040352b:	pushl %ebx
0x0040352c:	pushl %esi
0x0040352d:	movl %esi, 0xc(%ebp)
0x00403530:	movl %eax, 0xc(%esi)
0x00403533:	testb %al, $0xffffff82<UINT8>
0x00403535:	movl %ebx, 0x10(%esi)
0x00403538:	je 246
0x0040353e:	testb %al, $0x40<UINT8>
0x00403540:	jne 238
0x00403546:	testb %al, $0x1<UINT8>
0x00403548:	je 0x00403561
0x00403561:	movl %eax, 0xc(%esi)
0x00403564:	andl 0x4(%esi), $0x0<UINT8>
0x00403568:	andl 0xc(%ebp), $0x0<UINT8>
0x0040356c:	andl %eax, $0xffffffef<UINT8>
0x0040356f:	orl %eax, $0x2<UINT8>
0x00403572:	testw %ax, $0x10c<UINT16>
0x00403576:	movl 0xc(%esi), %eax
0x00403579:	jne 34
0x0040357b:	cmpl %esi, $0x40a068<UINT32>
0x00403581:	je 0x0040358b
0x0040358b:	pushl %ebx
0x0040358c:	call 0x004034fe
0x00403591:	testl %eax, %eax
0x00403593:	popl %ecx
0x00403594:	jne 7
0x00403596:	pushl %esi
0x00403597:	call 0x00405a97
0x00405a97:	incl 0x40abcc
0x00405a9d:	pushl $0x1000<UINT32>
0x00405aa2:	call 0x004034ec
0x00405aa7:	testl %eax, %eax
0x00405aa9:	popl %ecx
0x00405aaa:	movl %ecx, 0x4(%esp)
0x00405aae:	movl 0x8(%ecx), %eax
0x00405ab1:	je 13
0x00405ab3:	orl 0xc(%ecx), $0x8<UINT8>
0x00405ab7:	movl 0x18(%ecx), $0x1000<UINT32>
0x00405abe:	jmp 0x00405ad1
0x00405ad1:	movl %eax, 0x8(%ecx)
0x00405ad4:	andl 0x4(%ecx), $0x0<UINT8>
0x00405ad8:	movl (%ecx), %eax
0x00405ada:	ret

0x0040359c:	popl %ecx
0x0040359d:	testw 0xc(%esi), $0x108<UINT16>
0x004035a3:	pushl %edi
0x004035a4:	je 100
0x004035a6:	movl %eax, 0x8(%esi)
0x004035a9:	movl %edi, (%esi)
0x004035ab:	leal %ecx, 0x1(%eax)
0x004035ae:	movl (%esi), %ecx
0x004035b0:	movl %ecx, 0x18(%esi)
0x004035b3:	subl %edi, %eax
0x004035b5:	decl %ecx
0x004035b6:	testl %edi, %edi
0x004035b8:	movl 0x4(%esi), %ecx
0x004035bb:	jle 0x004035ca
0x004035ca:	cmpl %ebx, $0xffffffff<UINT8>
0x004035cd:	je 25
0x004035cf:	movl %ecx, %ebx
0x004035d1:	sarl %ecx, $0x5<UINT8>
0x004035d4:	movl %ecx, 0x40b2e0(,%ecx,4)
0x004035db:	movl %eax, %ebx
0x004035dd:	andl %eax, $0x1f<UINT8>
0x004035e0:	leal %eax, (%eax,%eax,8)
0x004035e3:	leal %eax, (%ecx,%eax,4)
0x004035e6:	jmp 0x004035ed
0x004035ed:	testb 0x4(%eax), $0x20<UINT8>
0x004035f1:	je 0x00403600
0x00403600:	movl %eax, 0x8(%esi)
0x00403603:	movb %cl, 0x8(%ebp)
0x00403606:	movb (%eax), %cl
0x00403608:	jmp 0x0040361e
0x0040361e:	cmpl 0xc(%ebp), %edi
0x00403621:	popl %edi
0x00403622:	je 0x0040362a
0x0040362a:	movl %eax, 0x8(%ebp)
0x0040362d:	andl %eax, $0xff<UINT32>
0x00403632:	jmp 0x0040363d
0x0040363d:	popl %esi
0x0040363e:	popl %ebx
0x0040363f:	popl %ebp
0x00403640:	ret

0x0040165b:	popl %ecx
0x0040165c:	popl %ecx
0x0040165d:	cmpl %eax, $0xffffffff<UINT8>
0x00401660:	jne 0x00401665
0x00401665:	incl (%esi)
0x00401667:	ret

0x00401913:	jmp 0x00401e35
0x00401e35:	movl %edi, -80(%ebp)
0x00401e38:	movb %bl, (%edi)
0x00401e3a:	testb %bl, %bl
0x00401e3c:	jne 0x00401715
0x00401715:	movl %ecx, -112(%ebp)
0x00401646:	movl %edx, (%ecx)
0x00401648:	movb (%edx), %al
0x0040164a:	incl (%ecx)
0x0040164c:	movzbl %eax, %al
0x0040164f:	jmp 0x0040165d
0x0040173f:	xorl %eax, %eax
0x00401e42:	popl %edi
0x00401e43:	popl %esi
0x00401e44:	movl %ecx, 0x1dc(%ebp)
0x00401e4a:	movl %eax, -68(%ebp)
0x00401e4d:	xorl %ecx, %ebp
0x00401e4f:	popl %ebx
0x00401e50:	call 0x00403793
0x00401e55:	addl %ebp, $0x1e0<UINT32>
0x00401e5b:	leave
0x00401e5c:	ret

0x00401087:	movl -32(%ebp), %eax
0x0040108a:	pushl %esi
0x0040108b:	pushl -28(%ebp)
0x0040108e:	call 0x0040160b
0x0040160b:	cmpl 0x4(%esp), $0x0<UINT8>
0x00401610:	je 0x00401634
0x00401634:	ret

0x00401093:	addl %esp, $0x18<UINT8>
0x00401096:	orl -4(%ebp), $0xffffffff<UINT8>
0x0040109a:	call 0x004010ad
0x004010ad:	pushl %esi
0x004010ae:	pushl $0x1<UINT8>
0x004010b0:	call 0x00401560
0x00401560:	movl %eax, 0x4(%esp)
0x00401564:	cmpl %eax, $0x14<UINT8>
0x00401567:	jnl 11
0x00401569:	addl %eax, $0x10<UINT8>
0x0040156c:	pushl %eax
0x0040156d:	call 0x0040335f
0x00401572:	popl %ecx
0x00401573:	ret

0x004010b5:	popl %ecx
0x004010b6:	popl %ecx
0x004010b7:	ret

0x0040109f:	movl %eax, -32(%ebp)
0x004010a2:	call 0x00401ebb
0x004010a7:	ret

0x0040102a:	pushl $0xc<UINT8>
0x0040102c:	pushl $0x0<UINT8>
0x0040102e:	pushl $0x401000<UINT32>
0x00401033:	call 0x00401188
0x00401188:	pushl %ebx
0x00401189:	pushl %edi
0x0040118a:	movl %edi, 0xc(%esp)
0x0040118e:	xorl %ebx, %ebx
0x00401190:	testl %edi, %edi
0x00401192:	jne 0x004011a4
0x004011a4:	pushl %esi
0x004011a5:	pushl $0x8c<UINT32>
0x004011aa:	pushl $0x1<UINT8>
0x004011ac:	call 0x00402705
0x004011b1:	movl %esi, %eax
0x004011b3:	testl %esi, %esi
0x004011b5:	popl %ecx
0x004011b6:	popl %ecx
0x004011b7:	je 67
0x004011b9:	pushl %esi
0x004011ba:	call 0x00401ffb
0x00401ffb:	movl %eax, 0x4(%esp)
0x00401fff:	movl 0x54(%eax), $0x40a2d8<UINT32>
0x00402006:	movl 0x14(%eax), $0x1<UINT32>
0x0040200d:	ret

0x004011bf:	movl %eax, 0x1c(%esp)
0x004011c3:	popl %ecx
0x004011c4:	pushl %esi
0x004011c5:	pushl $0x4<UINT8>
0x004011c7:	pushl %esi
0x004011c8:	pushl $0x4010f6<UINT32>
0x004011cd:	pushl 0x24(%esp)
0x004011d1:	movl 0x4c(%esi), %edi
0x004011d4:	pushl $0x0<UINT8>
0x004011d6:	movl 0x50(%esi), %eax
0x004011d9:	call CreateThread@kernel32.dll
CreateThread@kernel32.dll: API Node	
0x004011df:	movl %edi, %eax
0x004011e1:	testl %edi, %edi
0x004011e3:	movl 0x4(%esi), %edi
0x004011e6:	je 12
0x004011e8:	pushl %edi
0x004011e9:	call ResumeThread@kernel32.dll
ResumeThread@kernel32.dll: API Node	
0x004011ef:	cmpl %eax, $0xffffffff<UINT8>
0x004011f2:	jne 31
0x004011f4:	call GetLastError@kernel32.dll
GetLastError@kernel32.dll: API Node	
0x004011fa:	movl %ebx, %eax
0x004011fc:	pushl %esi
0x004011fd:	call 0x0040260f
0x00401202:	testl %ebx, %ebx
0x00401204:	popl %ecx
0x00401205:	je 7
0x00401207:	pushl %ebx
0x00401208:	call 0x00402692
0x00402692:	pushl %esi
0x00402693:	call 0x0040200e
0x0040200e:	pushl %ebx
0x0040200f:	pushl %esi
0x00402010:	call GetLastError@kernel32.dll
0x00402016:	pushl 0x40a2d0
0x0040201c:	movl %ebx, %eax
0x0040201e:	call FlsGetValue@kernel32.dll
FlsGetValue@kernel32.dll: API Node	
0x00402024:	movl %esi, %eax
0x00402026:	testl %esi, %esi
0x00402028:	jne 0x00402073
0x00402073:	pushl %ebx
0x00402074:	call SetLastError@kernel32.dll
SetLastError@kernel32.dll: API Node	
0x0040207a:	movl %eax, %esi
0x0040207c:	popl %esi
0x0040207d:	popl %ebx
0x0040207e:	ret

0x00402698:	movl %ecx, 0x8(%esp)
0x0040269c:	movl 0xc(%eax), %ecx
0x0040269f:	xorl %esi, %esi
0x004026a1:	cmpl %ecx, 0x40a360(,%esi,8)
0x004026a8:	je 0x004026c8
0x004026aa:	incl %esi
0x004026ab:	cmpl %esi, $0x2d<UINT8>
0x004026ae:	jb 0x004026a1
0x004026c8:	call 0x0040200e
0x004026cd:	movl %ecx, 0x40a364(,%esi,8)
0x004026d4:	movl 0x8(%eax), %ecx
0x004026d7:	popl %esi
0x004026d8:	ret

0x0040120d:	popl %ecx
0x0040120e:	orl %eax, $0xffffffff<UINT8>
0x00401211:	jmp 0x00401215
0x00401215:	popl %esi
0x00401216:	popl %edi
0x00401217:	popl %ebx
0x00401218:	ret

0x00401038:	pushl $0xfffffffb<UINT8>
0x0040103a:	pushl $0x40812c<UINT32>
0x0040103f:	call 0x00401052
0x00401761:	xorl %eax, %eax
0x00401763:	orl -56(%ebp), $0xffffffff<UINT8>
0x00401767:	movl -108(%ebp), %eax
0x0040176a:	movl -100(%ebp), %eax
0x0040176d:	movl -88(%ebp), %eax
0x00401770:	movl -76(%ebp), %eax
0x00401773:	movl -48(%ebp), %eax
0x00401776:	movl -92(%ebp), %eax
0x00401779:	jmp 0x00401e35
0x00401918:	movsbl %eax, %bl
0x0040191b:	cmpl %eax, $0x67<UINT8>
0x0040191e:	jg 570
0x00401924:	cmpl %eax, $0x65<UINT8>
0x00401927:	jge 128
0x0040192d:	cmpl %eax, $0x58<UINT8>
0x00401930:	jg 0x00401a08
0x00401a08:	subl %eax, $0x5a<UINT8>
0x00401a0b:	je 82
0x00401a0d:	subl %eax, $0x9<UINT8>
0x00401a10:	je -57
0x00401a12:	decl %eax
0x00401a13:	jne 788
0x00401a19:	orl -48(%ebp), $0x40<UINT8>
0x00401a1d:	movl -64(%ebp), $0xa<UINT32>
0x00401a24:	movl %ebx, -48(%ebp)
0x00401a27:	movl %esi, $0x8000<UINT32>
0x00401a2c:	testl %esi, %ebx
0x00401a2e:	je 0x00401c4f
0x00401c4f:	addl -52(%ebp), $0x4<UINT8>
0x00401c53:	testb %bl, $0x20<UINT8>
0x00401c56:	movl %eax, -52(%ebp)
0x00401c59:	je 0x00401c6d
0x00401c6d:	testb %bl, $0x40<UINT8>
0x00401c70:	movl %eax, -4(%eax)
0x00401c73:	jne 0x00401c64
0x00401c64:	cltd
0x00401c65:	jmp 0x00401c77
0x00401c77:	testb %bl, $0x40<UINT8>
0x00401c7a:	je 21
0x00401c7c:	testl %edx, %edx
0x00401c7e:	jg 17
0x00401c80:	jl 0x00401c86
0x00401c86:	negl %eax
0x00401c88:	adcl %edx, $0x0<UINT8>
0x00401c8b:	negl %edx
0x00401c8d:	orb -47(%ebp), $0x1<UINT8>
0x00401c91:	testl -48(%ebp), %esi
0x00401c94:	movl %ebx, %eax
0x00401c96:	movl %edi, %edx
0x00401c98:	jne 2
0x00401c9a:	xorl %edi, %edi
0x00401c9c:	cmpl -56(%ebp), $0x0<UINT8>
0x00401ca0:	jnl 9
0x00401ca2:	movl -56(%ebp), $0x1<UINT32>
0x00401ca9:	jmp 0x00401cbc
0x00401cbc:	movl %eax, %ebx
0x00401cbe:	orl %eax, %edi
0x00401cc0:	jne 0x00401cc6
0x00401cc6:	leal %esi, 0x1d3(%ebp)
0x00401ccc:	movl %eax, -56(%ebp)
0x00401ccf:	decl -56(%ebp)
0x00401cd2:	testl %eax, %eax
0x00401cd4:	jg 0x00401cdc
0x00401cdc:	movl %eax, -64(%ebp)
0x00401cdf:	cltd
0x00401ce0:	pushl %edx
0x00401ce1:	pushl %eax
0x00401ce2:	pushl %edi
0x00401ce3:	pushl %ebx
0x00401ce4:	call 0x004037b0
0x004037b0:	pushl %esi
0x004037b1:	movl %eax, 0x14(%esp)
0x004037b5:	orl %eax, %eax
0x004037b7:	jne 40
0x004037b9:	movl %ecx, 0x10(%esp)
0x004037bd:	movl %eax, 0xc(%esp)
0x004037c1:	xorl %edx, %edx
0x004037c3:	divl %eax, %ecx
0x004037c5:	movl %ebx, %eax
0x004037c7:	movl %eax, 0x8(%esp)
0x004037cb:	divl %eax, %ecx
0x004037cd:	movl %esi, %eax
0x004037cf:	movl %eax, %ebx
0x004037d1:	mull %eax, 0x10(%esp)
0x004037d5:	movl %ecx, %eax
0x004037d7:	movl %eax, %esi
0x004037d9:	mull %eax, 0x10(%esp)
0x004037dd:	addl %edx, %ecx
0x004037df:	jmp 0x00403828
0x00403828:	subl %eax, 0x8(%esp)
0x0040382c:	sbbl %edx, 0xc(%esp)
0x00403830:	negl %edx
0x00403832:	negl %eax
0x00403834:	sbbl %edx, $0x0<UINT8>
0x00403837:	movl %ecx, %edx
0x00403839:	movl %edx, %ebx
0x0040383b:	movl %ebx, %ecx
0x0040383d:	movl %ecx, %eax
0x0040383f:	movl %eax, %esi
0x00403841:	popl %esi
0x00403842:	ret $0x10<UINT16>

0x00401ce9:	addl %ecx, $0x30<UINT8>
0x00401cec:	cmpl %ecx, $0x39<UINT8>
0x00401cef:	movl -116(%ebp), %ebx
0x00401cf2:	movl %ebx, %eax
0x00401cf4:	movl %edi, %edx
0x00401cf6:	jle 0x00401cfb
0x00401cfb:	movb (%esi), %cl
0x00401cfd:	decl %esi
0x00401cfe:	jmp 0x00401ccc
0x00401cd6:	movl %eax, %ebx
0x00401cd8:	orl %eax, %edi
0x00401cda:	je 0x00401d00
0x00401d00:	leal %eax, 0x1d3(%ebp)
0x00401d06:	subl %eax, %esi
0x00401d08:	incl %esi
0x00401d09:	testb -47(%ebp), $0x2<UINT8>
0x00401d0d:	movl -64(%ebp), %eax
0x00401d10:	movl -60(%ebp), %esi
0x00401d13:	je 0x00401d2d
0x00401d2d:	cmpl -100(%ebp), $0x0<UINT8>
0x00401d31:	jne 235
0x00401d37:	movl %ebx, -48(%ebp)
0x00401d3a:	testb %bl, $0x40<UINT8>
0x00401d3d:	je 38
0x00401d3f:	testb %bh, $0x1<UINT8>
0x00401d42:	je 6
0x00401d44:	movb -72(%ebp), $0x2d<UINT8>
0x00401d48:	jmp 0x00401d5e
0x00401d5e:	movl -76(%ebp), $0x1<UINT32>
0x00401d65:	movl %esi, -88(%ebp)
0x00401d68:	subl %esi, -76(%ebp)
0x00401d6b:	subl %esi, -64(%ebp)
0x00401d6e:	testb %bl, $0xc<UINT8>
0x00401d71:	jne 17
0x00401d73:	pushl -84(%ebp)
0x00401d76:	leal %eax, -68(%ebp)
0x00401d79:	pushl %esi
0x00401d7a:	pushl $0x20<UINT8>
0x00401d7c:	call 0x00401668
0x00401668:	pushl %ebp
0x00401669:	movl %ebp, %esp
0x0040166b:	pushl %esi
0x0040166c:	movl %esi, %eax
0x0040166e:	jmp 0x00401683
0x00401683:	cmpl 0xc(%ebp), $0x0<UINT8>
0x00401687:	jg -25
0x00401689:	popl %esi
0x0040168a:	popl %ebp
0x0040168b:	ret

0x00401d81:	addl %esp, $0xc<UINT8>
0x00401d84:	pushl -76(%ebp)
0x00401d87:	movl %edi, -84(%ebp)
0x00401d8a:	leal %eax, -68(%ebp)
0x00401d8d:	leal %ecx, -72(%ebp)
0x00401d90:	call 0x0040168c
0x0040168c:	testb 0xc(%edi), $0x40<UINT8>
0x00401690:	pushl %ebx
0x00401691:	pushl %esi
0x00401692:	movl %esi, %eax
0x00401694:	movl %ebx, %ecx
0x00401696:	je 0x004016b9
0x004016b9:	cmpl 0xc(%esp), $0x0<UINT8>
0x004016be:	jg 0x004016a6
0x004016a6:	movb %al, (%ebx)
0x004016a8:	decl 0xc(%esp)
0x004016ac:	movl %ecx, %edi
0x004016ae:	call 0x00401635
0x004016b3:	incl %ebx
0x004016b4:	cmpl (%esi), $0xffffffff<UINT8>
0x004016b7:	je 7
0x004016c0:	popl %esi
0x004016c1:	popl %ebx
0x004016c2:	ret

0x00401d95:	testb %bl, $0x8<UINT8>
0x00401d98:	popl %ecx
0x00401d99:	je 0x00401daf
0x00401daf:	cmpl -92(%ebp), $0x0<UINT8>
0x00401db3:	je 0x00401dfc
0x00401dfc:	pushl -64(%ebp)
0x00401dff:	movl %ecx, -60(%ebp)
0x00401e02:	leal %eax, -68(%ebp)
0x00401e05:	call 0x0040168c
0x00401e0a:	popl %ecx
0x00401e0b:	testb -48(%ebp), $0x4<UINT8>
0x00401e0f:	je 0x00401e22
0x00401e22:	cmpl -96(%ebp), $0x0<UINT8>
0x00401e26:	je 0x00401e35
0x00401044:	addl %esp, $0x18<UINT8>
0x00401047:	pushl $0x64<UINT8>
0x00401049:	call Sleep@kernel32.dll
Sleep@kernel32.dll: API Node	
0x0040104f:	xorl %eax, %eax
0x00401051:	ret

0x004013ce:	addl %esp, $0xc<UINT8>
0x004013d1:	movl %esi, %eax
0x004013d3:	movl -40(%ebp), %esi
0x004013d6:	cmpl -28(%ebp), %edi
0x004013d9:	jne 6
0x004013db:	pushl %esi
0x004013dc:	call 0x0040246b
0x0040246b:	pushl $0x0<UINT8>
0x0040246d:	pushl $0x0<UINT8>
0x0040246f:	pushl 0xc(%esp)
0x00402473:	call 0x004023a8
0x004023a8:	pushl $0x8<UINT8>
0x004023aa:	pushl $0x408288<UINT32>
0x004023af:	call 0x00401e80
0x004023b4:	pushl $0x8<UINT8>
0x004023b6:	call 0x00403414
0x004023bb:	popl %ecx
0x004023bc:	xorl %edi, %edi
0x004023be:	movl -4(%ebp), %edi
0x004023c1:	xorl %esi, %esi
0x004023c3:	incl %esi
0x004023c4:	cmpl 0x40ac2c, %esi
0x004023ca:	jne 0x004023dc
0x004023dc:	movl 0x40ac28, %esi
0x004023e2:	movb %al, 0x10(%ebp)
0x004023e5:	movb 0x40ac24, %al
0x004023ea:	cmpl 0xc(%ebp), %edi
0x004023ed:	jne 55
0x004023ef:	cmpl 0x40b3e8, %edi
0x004023f5:	je 31
0x004023f7:	movl %eax, 0x40b3e4
0x004023fc:	subl %eax, $0x4<UINT8>
0x004023ff:	movl 0x40b3e4, %eax
0x00402404:	cmpl %eax, 0x40b3e8
0x0040240a:	jb 0x00402416
0x0040240c:	movl %eax, (%eax)
0x0040240e:	cmpl %eax, %edi
0x00402410:	je -27
0x00402412:	call 0x00402fc6
0x00402fc6:	pushl $0xc<UINT8>
0x00402fc8:	pushl $0x4086d0<UINT32>
0x00402fcd:	call 0x00401e80
0x00402fd2:	movl -28(%ebp), $0x409354<UINT32>
0x00402fd9:	cmpl -28(%ebp), $0x409354<UINT32>
0x00402fe0:	jae 0x00403004
0x00403004:	call 0x00401ebb
0x00403009:	ret

0x00402414:	jmp 0x004023f7
0x00402416:	pushl $0x40a028<UINT32>
0x0040241b:	movl %eax, $0x40a020<UINT32>
0x00402420:	call 0x00402326
0x00402326:	pushl %esi
0x00402327:	movl %esi, %eax
0x00402329:	jmp 0x00402336
0x00402336:	cmpl %esi, 0x8(%esp)
0x0040233a:	jb 0x0040232b
0x0040232b:	movl %eax, (%esi)
0x0040232d:	testl %eax, %eax
0x0040232f:	je 0x00402333
0x00402333:	addl %esi, $0x4<UINT8>
0x00402331:	call 0x004014cb
0x004014cb:	call 0x004032b8
0x004032b8:	pushl $0x1<UINT8>
0x004032ba:	call 0x004031e3
0x004031e3:	pushl $0x14<UINT8>
0x004031e5:	pushl $0x4086f0<UINT32>
0x004031ea:	call 0x00401e80
0x004031ef:	xorl %edi, %edi
0x004031f1:	movl -28(%ebp), %edi
0x004031f4:	movl -36(%ebp), %edi
0x004031f7:	pushl $0x1<UINT8>
0x004031f9:	call 0x00403414
0x004031fe:	popl %ecx
0x004031ff:	movl -4(%ebp), %edi
0x00403202:	xorl %esi, %esi
0x00403204:	movl -32(%ebp), %esi
0x00403207:	cmpl %esi, 0x40c400
0x0040320d:	jge 0x00403294
0x00403213:	movl %eax, 0x40b3f4
0x00403218:	movl %eax, (%eax,%esi,4)
0x0040321b:	cmpl %eax, %edi
0x0040321d:	je 0x0040327b
0x0040321f:	testb 0xc(%eax), $0xffffff83<UINT8>
0x00403223:	je 0x0040327b
0x00403225:	pushl %eax
0x00403226:	pushl %esi
0x00403227:	call 0x0040150e
0x0040322c:	popl %ecx
0x0040322d:	popl %ecx
0x0040322e:	xorl %edx, %edx
0x00403230:	incl %edx
0x00403231:	movl -4(%ebp), %edx
0x00403234:	movl %eax, 0x40b3f4
0x00403239:	movl %eax, (%eax,%esi,4)
0x0040323c:	movl %ecx, 0xc(%eax)
0x0040323f:	testb %cl, $0xffffff83<UINT8>
0x00403242:	je 47
0x00403244:	cmpl 0x8(%ebp), %edx
0x00403247:	jne 17
0x00403249:	pushl %eax
0x0040324a:	call 0x004031b5
0x004031b5:	pushl %esi
0x004031b6:	movl %esi, 0x8(%esp)
0x004031ba:	pushl %esi
0x004031bb:	call 0x00403158
0x00403158:	pushl %ebx
0x00403159:	pushl %esi
0x0040315a:	movl %esi, 0xc(%esp)
0x0040315e:	movl %eax, 0xc(%esi)
0x00403161:	movl %ecx, %eax
0x00403163:	andb %cl, $0x3<UINT8>
0x00403166:	xorl %ebx, %ebx
0x00403168:	cmpb %cl, $0x2<UINT8>
0x0040316b:	jne 0x004031a7
0x004031a7:	movl %eax, 0x8(%esi)
0x004031aa:	andl 0x4(%esi), $0x0<UINT8>
0x004031ae:	movl (%esi), %eax
0x004031b0:	popl %esi
0x004031b1:	movl %eax, %ebx
0x004031b3:	popl %ebx
0x004031b4:	ret

0x004031c0:	testl %eax, %eax
0x004031c2:	popl %ecx
0x004031c3:	je 0x004031ca
0x004031ca:	testb 0xd(%esi), $0x40<UINT8>
0x004031ce:	je 0x004031df
0x004031df:	xorl %eax, %eax
0x004031e1:	popl %esi
0x004031e2:	ret

0x0040324f:	popl %ecx
0x00403250:	cmpl %eax, $0xffffffff<UINT8>
0x00403253:	je 0x00403273
0x00403255:	incl -28(%ebp)
0x00403258:	jmp 0x00403273
0x00403273:	movl -4(%ebp), %edi
0x00403276:	call 0x00403283
0x00403283:	movl %eax, 0x40b3f4
0x00403288:	pushl (%eax,%esi,4)
0x0040328b:	pushl %esi
0x0040328c:	call 0x00401560
0x00403291:	popl %ecx
0x00403292:	popl %ecx
0x00403293:	ret

0x0040327b:	incl %esi
0x0040327c:	jmp 0x00403204
0x0040316d:	testw %ax, $0x108<UINT16>
0x00403171:	je 0x004031a7
0x00403173:	movl %eax, 0x8(%esi)
0x00403176:	pushl %edi
0x00403177:	movl %edi, (%esi)
0x00403179:	subl %edi, %eax
0x0040317b:	testl %edi, %edi
0x0040317d:	jle 39
0x0040317f:	pushl %edi
0x00403180:	pushl %eax
0x00403181:	pushl 0x10(%esi)
0x00403184:	call 0x00405811
0x00405811:	pushl $0xc<UINT8>
0x00405813:	pushl $0x408ea8<UINT32>
0x00405818:	call 0x00401e80
0x0040581d:	movl %ebx, 0x8(%ebp)
0x00405820:	cmpl %ebx, 0x40b2c8
0x00405826:	jae 120
0x00405828:	movl %eax, %ebx
0x0040582a:	sarl %eax, $0x5<UINT8>
0x0040582d:	leal %edi, 0x40b2e0(,%eax,4)
0x00405834:	movl %eax, %ebx
0x00405836:	andl %eax, $0x1f<UINT8>
0x00405839:	leal %esi, (%eax,%eax,8)
0x0040583c:	shll %esi, $0x2<UINT8>
0x0040583f:	movl %eax, (%edi)
0x00405841:	testb 0x4(%eax,%esi), $0x1<UINT8>
0x00405846:	je 88
0x00405848:	pushl %ebx
0x00405849:	call 0x00406f09
0x00406f09:	pushl $0x8<UINT8>
0x00406f0b:	pushl $0x409220<UINT32>
0x00406f10:	call 0x00401e80
0x00406f15:	movl %edi, 0x8(%ebp)
0x00406f18:	movl %ecx, %edi
0x00406f1a:	sarl %ecx, $0x5<UINT8>
0x00406f1d:	movl %eax, %edi
0x00406f1f:	andl %eax, $0x1f<UINT8>
0x00406f22:	leal %eax, (%eax,%eax,8)
0x00406f25:	movl %ecx, 0x40b2e0(,%ecx,4)
0x00406f2c:	leal %esi, (%ecx,%eax,4)
0x00406f2f:	xorl %ebx, %ebx
0x00406f31:	cmpl 0x8(%esi), %ebx
0x00406f34:	jne 0x00406f77
0x00406f77:	movl %eax, %edi
0x00406f79:	sarl %eax, $0x5<UINT8>
0x00406f7c:	andl %edi, $0x1f<UINT8>
0x00406f7f:	leal %ecx, (%edi,%edi,8)
0x00406f82:	movl %eax, 0x40b2e0(,%eax,4)
0x00406f89:	leal %eax, 0xc(%eax,%ecx,4)
0x00406f8d:	pushl %eax
0x00406f8e:	call EnterCriticalSection@kernel32.dll
0x00406f94:	xorl %eax, %eax
0x00406f96:	incl %eax
0x00406f97:	call 0x00401ebb
0x00406f9c:	ret

0x0040584e:	popl %ecx
0x0040584f:	andl -4(%ebp), $0x0<UINT8>
0x00405853:	movl %eax, (%edi)
0x00405855:	testb 0x4(%eax,%esi), $0x1<UINT8>
0x0040585a:	je 20
0x0040585c:	pushl 0x10(%ebp)
0x0040585f:	pushl 0xc(%ebp)
0x00405862:	pushl %ebx
0x00405863:	call 0x00405655
0x00405655:	pushl %ebp
0x00405656:	leal %ebp, -932(%esp)
0x0040565d:	subl %esp, $0x424<UINT32>
0x00405663:	movl %eax, 0x40a6d0
0x00405668:	xorl %ecx, %ecx
0x0040566a:	xorl %eax, %ebp
0x0040566c:	cmpl 0x3b4(%ebp), %ecx
0x00405672:	pushl %edi
0x00405673:	movl %edi, 0x3b0(%ebp)
0x00405679:	movl 0x3a0(%ebp), %eax
0x0040567f:	movl -124(%ebp), %edi
0x00405682:	movl -116(%ebp), %ecx
0x00405685:	movl -120(%ebp), %ecx
0x00405688:	jne 0x00405691
0x00405691:	movl %eax, 0x3ac(%ebp)
0x00405697:	pushl %ebx
0x00405698:	movl %ebx, 0x3ac(%ebp)
0x0040569e:	andl %eax, $0x1f<UINT8>
0x004056a1:	sarl %ebx, $0x5<UINT8>
0x004056a4:	pushl %esi
0x004056a5:	leal %esi, (%eax,%eax,8)
0x004056a8:	leal %ebx, 0x40b2e0(,%ebx,4)
0x004056af:	movl %eax, (%ebx)
0x004056b1:	shll %esi, $0x2<UINT8>
0x004056b4:	testb 0x4(%eax,%esi), $0x20<UINT8>
0x004056b9:	je 0x004056cf
0x004056cf:	movl %eax, (%ebx)
0x004056d1:	addl %eax, %esi
0x004056d3:	testb 0x4(%eax), $0xffffff80<UINT8>
0x004056d7:	je 140
0x004056dd:	cmpl 0x3b4(%ebp), %ecx
0x004056e3:	movl -112(%ebp), %edi
0x004056e6:	movl -104(%ebp), %ecx
0x004056e9:	jbe 221
0x004056ef:	jmp 0x004056f3
0x004056f3:	movl -108(%ebp), %ecx
0x004056f6:	movl %ecx, -112(%ebp)
0x004056f9:	subl %ecx, -124(%ebp)
0x004056fc:	leal %eax, -100(%ebp)
0x004056ff:	cmpl %ecx, 0x3b4(%ebp)
0x00405705:	jae 0x0040572e
0x00405707:	movl %edx, -112(%ebp)
0x0040570a:	incl -112(%ebp)
0x0040570d:	movb %dl, (%edx)
0x0040570f:	incl %ecx
0x00405710:	cmpb %dl, $0xa<UINT8>
0x00405713:	jne 0x0040571f
0x0040571f:	movb (%eax), %dl
0x00405721:	incl %eax
0x00405722:	incl -108(%ebp)
0x00405725:	cmpl -108(%ebp), $0x400<UINT32>
0x0040572c:	jl 0x004056ff
0x00405715:	incl -120(%ebp)
0x00405718:	movb (%eax), $0xd<UINT8>
0x0040571b:	incl %eax
0x0040571c:	incl -108(%ebp)
0x0040572e:	movl %edi, %eax
0x00405730:	leal %eax, -100(%ebp)
0x00405733:	subl %edi, %eax
0x00405735:	pushl $0x0<UINT8>
0x00405737:	leal %eax, -128(%ebp)
0x0040573a:	pushl %eax
0x0040573b:	pushl %edi
0x0040573c:	leal %eax, -100(%ebp)
0x0040573f:	pushl %eax
0x00405740:	movl %eax, (%ebx)
0x00405742:	pushl (%eax,%esi)
0x00405745:	call WriteFile@kernel32.dll
WriteFile@kernel32.dll: API Node	
0x0040574b:	testl %eax, %eax
0x0040574d:	je 62
0x0040574f:	movl %eax, -128(%ebp)
0x00405752:	addl -116(%ebp), %eax
0x00405755:	cmpl %eax, %edi
0x00405757:	jl 61
0x00405759:	movl %eax, -112(%ebp)
0x0040575c:	subl %eax, -124(%ebp)
0x0040575f:	cmpl %eax, 0x3b4(%ebp)
0x00405765:	jb -118
0x00405767:	jmp 0x00405796
0x00405796:	movl %eax, -116(%ebp)
0x00405799:	testl %eax, %eax
0x0040579b:	jne 0x004057f6
0x004057f6:	subl %eax, -120(%ebp)
0x004057f9:	popl %esi
0x004057fa:	popl %ebx
0x004057fb:	movl %ecx, 0x3a0(%ebp)
0x00405801:	xorl %ecx, %ebp
0x00405803:	popl %edi
0x00405804:	call 0x00403793
0x00405809:	addl %ebp, $0x3a4<UINT32>
0x0040580f:	leave
0x00405810:	ret

0x00405868:	addl %esp, $0xc<UINT8>
0x0040586b:	movl -28(%ebp), %eax
0x0040586e:	jmp 0x00405887
0x00405887:	orl -4(%ebp), $0xffffffff<UINT8>
0x0040588b:	call 0x00405898
0x00405898:	pushl %ebx
0x00405899:	call 0x00406fa9
0x00406fa9:	movl %eax, 0x4(%esp)
0x00406fad:	movl %ecx, %eax
0x00406faf:	andl %eax, $0x1f<UINT8>
0x00406fb2:	sarl %ecx, $0x5<UINT8>
0x00406fb5:	movl %ecx, 0x40b2e0(,%ecx,4)
0x00406fbc:	leal %eax, (%eax,%eax,8)
0x00406fbf:	leal %eax, 0xc(%ecx,%eax,4)
0x00406fc3:	pushl %eax
0x00406fc4:	call LeaveCriticalSection@kernel32.dll
0x00406fca:	ret

0x0040589e:	popl %ecx
0x0040589f:	ret

0x00405890:	movl %eax, -28(%ebp)
0x00405893:	jmp 0x004058b6
0x004058b6:	call 0x00401ebb
0x004058bb:	ret

0x00403189:	addl %esp, $0xc<UINT8>
0x0040318c:	cmpl %eax, %edi
0x0040318e:	jne 0x0040319f
0x0040319f:	orl 0xc(%esi), $0x20<UINT8>
0x004031a3:	orl %ebx, $0xffffffff<UINT8>
0x004031a6:	popl %edi
0x004031c5:	orl %eax, $0xffffffff<UINT8>
0x004031c8:	popl %esi
0x004031c9:	ret

0x00403294:	orl -4(%ebp), $0xffffffff<UINT8>
0x00403298:	call 0x004032af
0x004032af:	pushl $0x1<UINT8>
0x004032b1:	call 0x0040335f
0x004032b6:	popl %ecx
0x004032b7:	ret

0x0040329d:	cmpl 0x8(%ebp), $0x1<UINT8>
0x004032a1:	movl %eax, -28(%ebp)
0x004032a4:	je 0x004032a9
0x004032a9:	call 0x00401ebb
0x004032ae:	ret

0x004032bf:	popl %ecx
0x004032c0:	ret

0x004014d0:	cmpb 0x40ac24, $0x0<UINT8>
0x004014d7:	je 0x004014de
0x004014de:	ret

0x0040233c:	popl %esi
0x0040233d:	ret

0x00402425:	popl %ecx
0x00402426:	pushl $0x40a030<UINT32>
0x0040242b:	movl %eax, $0x40a02c<UINT32>
0x00402430:	call 0x00402326
0x00402435:	popl %ecx
0x00402436:	orl -4(%ebp), $0xffffffff<UINT8>
0x0040243a:	call 0x00402457
0x00402457:	cmpl 0x10(%ebp), %edi
0x0040245a:	je 0x00402464
0x00402464:	ret

0x0040243f:	cmpl 0x10(%ebp), %edi
0x00402442:	jne 33
0x00402444:	movl 0x40ac2c, %esi
0x0040244a:	pushl 0x8(%ebp)
0x0040244d:	call 0x004022e4
0x004022e4:	pushl $0x40827c<UINT32>
0x004022e9:	call GetModuleHandleA@kernel32.dll
0x004022ef:	testl %eax, %eax
0x004022f1:	je 0x00402309
0x00402309:	pushl 0x4(%esp)
0x0040230d:	call ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
