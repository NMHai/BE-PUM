0x00401131:	pushl %ebp
0x00401132:	movl %ebp, %esp
0x00401134:	pushl $0xffffffff<UINT8>
0x00401136:	pushl $0x4050a8<UINT32>
0x0040113b:	pushl $0x402584<UINT32>
0x00401140:	movl %eax, %fs:0
0x00401146:	pushl %eax
0x00401147:	movl %fs:0, %esp
0x0040114e:	subl %esp, $0x10<UINT8>
0x00401151:	pushl %ebx
0x00401152:	pushl %esi
0x00401153:	pushl %edi
0x00401154:	movl -24(%ebp), %esp
0x00401157:	call GetVersion@kernel32.dll
GetVersion@kernel32.dll: API Node	
0x0040115d:	xorl %edx, %edx
0x0040115f:	movb %dl, %ah
0x00401161:	movl 0x406950, %edx
0x00401167:	movl %ecx, %eax
0x00401169:	andl %ecx, $0xff<UINT32>
0x0040116f:	movl 0x40694c, %ecx
0x00401175:	shll %ecx, $0x8<UINT8>
0x00401178:	addl %ecx, %edx
0x0040117a:	movl 0x406948, %ecx
0x00401180:	shrl %eax, $0x10<UINT8>
0x00401183:	movl 0x406944, %eax
0x00401188:	pushl $0x0<UINT8>
0x0040118a:	call 0x0040244f
0x0040244f:	xorl %eax, %eax
0x00402451:	pushl $0x0<UINT8>
0x00402453:	cmpl 0x8(%esp), %eax
0x00402457:	pushl $0x1000<UINT32>
0x0040245c:	sete %al
0x0040245f:	pushl %eax
0x00402460:	call HeapCreate@kernel32.dll
HeapCreate@kernel32.dll: API Node	
0x00402466:	testl %eax, %eax
0x00402468:	movl 0x406d08, %eax
0x0040246d:	je 0x00402484
0x0040246f:	call 0x00403585
0x00402484:	xorl %eax, %eax
0x00403585:	pushl $0x140<UINT32>
0x0040358a:	pushl $0x0<UINT8>
0x0040358c:	pushl 0x406d08
0x00403592:	call HeapAlloc@kernel32.dll
HeapAlloc@kernel32.dll: API Node	
0x00403598:	testl %eax, %eax
0x00402486:	ret

0x0040118f:	popl %ecx
0x00401190:	testl %eax, %eax
0x00401192:	jne 8
0x00401194:	pushl $0x1c<UINT8>
0x00401196:	call 0x00401235
0x00401235:	cmpl 0x406928, $0x2<UINT8>
0x0040123c:	je 5
0x0040123e:	call 0x0040265c
0x0040265c:	movl %eax, 0x406928
0x00402661:	cmpl %eax, $0x1<UINT8>
0x00402664:	je 13
0x00402666:	testl %eax, %eax
0x00402668:	jne 42
0x0040266a:	cmpl 0x40609c, $0x1<UINT8>
0x00402671:	jne 33
0x00402673:	pushl $0xfc<UINT32>
0x00402678:	call 0x00402695
0x00402695:	pushl %ebp
0x00402696:	movl %ebp, %esp
0x00402698:	subl %esp, $0x1a4<UINT32>
0x0040269e:	movl %edx, 0x8(%ebp)
0x004026a1:	xorl %ecx, %ecx
0x004026a3:	movl %eax, $0x4063d0<UINT32>
0x004026a8:	cmpl %edx, (%eax)
0x004026aa:	je 0x004026b7
0x004026ac:	addl %eax, $0x8<UINT8>
0x004026af:	incl %ecx
0x004026b0:	cmpl %eax, $0x406460<UINT32>
0x004026b5:	jl 0x004026a8
0x0040359a:	movl 0x406ae8, %eax
0x0040359f:	jne 1
0x004026b7:	pushl %esi
0x004026b8:	movl %esi, %ecx
0x004026ba:	shll %esi, $0x3<UINT8>
0x004026bd:	cmpl %edx, 0x4063d0(%esi)
0x004026c3:	jne 284
0x004026c9:	movl %eax, 0x406928
0x004026ce:	cmpl %eax, $0x1<UINT8>
0x004026d1:	je 232
0x004026d7:	testl %eax, %eax
0x004026d9:	jne 13
0x004026db:	cmpl 0x40609c, $0x1<UINT8>
0x004026e2:	je 0x004027bf
0x004027bf:	leal %eax, 0x8(%ebp)
0x004027c2:	leal %esi, 0x4063d4(%esi)
0x004027c8:	pushl $0x0<UINT8>
0x004027ca:	pushl %eax
0x004027cb:	pushl (%esi)
0x004027cd:	call 0x00402990
0x00402990:	movl %ecx, 0x4(%esp)
0x00402994:	testl %ecx, $0x3<UINT32>
0x0040299a:	je 0x004029b0
0x004029b0:	movl %eax, (%ecx)
0x004029b2:	movl %edx, $0x7efefeff<UINT32>
0x004029b7:	addl %edx, %eax
0x004029b9:	xorl %eax, $0xffffffff<UINT8>
0x004029bc:	xorl %eax, %edx
0x004029be:	addl %ecx, $0x4<UINT8>
0x004029c1:	testl %eax, $0x81010100<UINT32>
0x004029c6:	je 0x004029b0
0x004029c8:	movl %eax, -4(%ecx)
0x004029cb:	testb %al, %al
0x004029cd:	je 0x00402a01
0x004029cf:	testb %ah, %ah
0x004029d1:	je 36
0x004029d3:	testl %eax, $0xff0000<UINT32>
0x004029d8:	je 0x004029ed
0x004029ed:	leal %eax, -2(%ecx)
0x004029f0:	movl %ecx, 0x4(%esp)
0x004029f4:	subl %eax, %ecx
0x004029f6:	ret

0x004027d2:	popl %ecx
0x004027d3:	pushl %eax
0x004027d4:	pushl (%esi)
0x004027d6:	pushl $0xfffffff4<UINT8>
0x004027d8:	call GetStdHandle@kernel32.dll
GetStdHandle@kernel32.dll: API Node	
0x004027de:	pushl %eax
0x004027df:	call WriteFile@kernel32.dll
WriteFile@kernel32.dll: API Node	
0x004027e5:	popl %esi
0x004027e6:	leave
0x004027e7:	ret

0x0040267d:	movl %eax, 0x406a90
0x00402682:	popl %ecx
0x00402683:	testl %eax, %eax
0x00402685:	je 0x00402689
0x00402689:	pushl $0xff<UINT32>
0x0040268e:	call 0x00402695
0x00402693:	popl %ecx
0x00402694:	ret

0x00401243:	pushl 0x4(%esp)
0x00401247:	call 0x00402695
0x00402a01:	leal %eax, -4(%ecx)
0x00402a04:	movl %ecx, 0x4(%esp)
0x00402a08:	subl %eax, %ecx
0x00402a0a:	ret

0x0040124c:	popl %ecx
0x0040124d:	pushl $0xff<UINT32>
0x00401252:	call ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
